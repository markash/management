package threesixty.hr.management.server.services.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import threesixty.hr.management.shared.services.rest.IBaseRestResourceClient;

public abstract class AbstractResourceClient<T, ID> implements IRestResourceClient, IBaseRestResourceClient<T, ID> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractResourceClient.class);
	
	
	private final String resourcePath;
	private final Class<T> resourceClass;
	private final GenericType<List<T>> listClass;
	
	public AbstractResourceClient(
			final String resourcePath, 
			final Class<T> resourceClass,
			final GenericType<List<T>> listClass) {
		
		this.resourcePath = resourcePath;
		this.resourceClass = resourceClass;
		this.listClass = listClass;
	}
	
	protected RestClientHelper helper() {
		return BEANS.get(RestClientHelper.class);
	}
	
	/**
	 * Retrieve the resource entries
	 * @return List of resource entries
	 */
	@Override
	public List<T> retrieveAll() {
		
		return query(null, new IRestParam[] {});
	}
	
	/**
	 * Retrieve the resource by id
	 * @return The resource identified by the id or null
	 */
	public T retrieveById(
			final ID id) {
		
		if (LOG.isInfoEnabled()) {
			
			LOG.info("Query: {}/{}", 
					this.resourcePath, 
					Optional.ofNullable(id).map(Object::toString).orElse(""));
		}
				
		T result =
				helper()
					.target(this.resourcePath)
					.path("/{id}").resolveTemplate("id", id)
					.request()
					.accept(MediaType.APPLICATION_JSON)
					.get(Response.class)
					.readEntity(resourceClass);
		
		LOG.info("Query: result={}", result);
		
		return result;
	}
	
	/**
	 * Query the REST endpoint for t
	 * @param path The path, i.e. /submitter/{id}/day/{date}
	 * @param templateValues The list of path values, i.e. id=8, date=2020-05-31
	 * @return The list of values returned from the REST endpoint for the query
	 * @deprecated
	 */
	public List<T> query(
			final String path,
			final NVPair...pathValues) {
		
		if (LOG.isInfoEnabled()) {
		
			NVPair[] pathValuesArray = Optional.ofNullable(pathValues).orElse(new NVPair[0]);
			
			String pathValuesString = 
					Arrays.stream(pathValuesArray)
						.map(p -> String.format("%s=%s", p.getName(), p.getValue()))
						.collect(Collectors.joining("&"));
		
			LOG.info("Query: {}/{}?{}", 
					this.resourcePath, 
					Optional.ofNullable(path).orElse(""), 
					pathValuesString);
		}
		
		if (StringUtility.isNullOrEmpty(path)) {
			
			return helper()
					.target(this.resourcePath)
					.request()
					.accept(MediaType.APPLICATION_JSON)
					.get(Response.class)
					.readEntity(listClass);
		}
	
		final Map<String, Object> templateValues = 
				Arrays.stream(pathValues)
					.collect(Collectors.toMap(NVPair::getName, NVPair::getValue));
			
		WebTarget webTarget =
			helper()
				.target(this.resourcePath)
				.path(path).resolveTemplates(templateValues);
		
		LOG.info(webTarget.getUri().toString());
		
		return webTarget.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(listClass);
	
	}
	
	/**
	 * Query the REST endpoint for t
	 * @param path The path, i.e. /submitter/{id}/day/{date}
	 * @param templateValues The list of path values, i.e. id=8, date=2020-05-31
	 * @return The list of values returned from the REST endpoint for the query
	 */
	public List<T> query(
			final String path,
			final IRestParam...parameters) {
		
		if (StringUtility.isNullOrEmpty(path)) {
			
			return helper()
					.target(this.resourcePath)
					.request()
					.accept(MediaType.APPLICATION_JSON)
					.get(Response.class)
					.readEntity(listClass);
		}
	
		Map<String, Object> pathParameters =
			Arrays.stream(parameters)
				.filter(p -> p.getType() == RestParamTypes.PATH)
				.collect(Collectors.toMap(IRestParam::getName, IRestParam::getValue));
		
		WebTarget webTarget =
			helper()
				.target(this.resourcePath)
				.path(path).resolveTemplates(pathParameters);
		
		List<IRestParam> queryParameters =
			Arrays.stream(parameters)
				.filter(p -> p.getType() == RestParamTypes.QUERY)
				.collect(Collectors.toList());
		
		for (IRestParam queryParameter : queryParameters) {
			
			webTarget = 
					webTarget.queryParam(
							queryParameter.getName(), 
							queryParameter.getValue());
		}
		
		LOG.info(webTarget.getUri().toString());
		
		return webTarget.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(listClass);
	
	}
}
