package threesixty.hr.infrastructure.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import threesixty.hr.core.party.Person;

@ApplicationScoped
public class PanachePersonRepository implements PanacheRepository<Person> {

    public List<Person> find(
			final List<Long> partyId) {
		
        List<Long> identifiers = 
            Optional.ofNullable(partyId).orElse(new ArrayList<>());
        
		return streamAll()
				.filter(party -> identifiers.contains(party.getPartyId()))
				.collect(Collectors.toList());
    }
    
    public List<Person> findByFullName(
        final String firstName,
        final String lastName) {
        
        final String query = 
                "firstName like :firstName " + 
            "AND lastName  like :lastName ";

        final Parameters parameters = 
            Parameters
                .with("firstName", firstName)
                .and("lastName", lastName);

        return find(query, parameters).list();
	}
}
