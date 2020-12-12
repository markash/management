package threesixty.hr.management.client.field.time;

import org.eclipse.scout.rt.client.ui.form.fields.mode.AbstractMode;
import org.eclipse.scout.rt.client.ui.form.fields.modeselector.AbstractModeSelectorField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@ClassId("e668b8b7-39a2-4718-abfd-28f6083addd5")
public abstract class AbstractTimeModeSelectorField extends AbstractModeSelectorField<Long> {

	@Override
	protected void execInitField() {
		setValue(1L);
	}

	@Override
	protected String getConfiguredFieldStyle() {
		return FIELD_STYLE_ALTERNATIVE;
	}

//	@Override
//	protected int getConfiguredWidthInPixel() {
//		return 500;
//	}

//	@Override
//	protected double getConfiguredGridWeightX() {
//		return 0;
//	}

	@Order(10)
	@ClassId("f4e9874d-7b28-4b5c-a1e4-d13b659dc0ac")
	public class TimeIntervalMode extends AbstractMode<Long> {

		@Override
		protected Long getConfiguredRef() {
			return 1L;
		}

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("TimeInterval");
		}
	}

	@Order(20)
	@ClassId("f5fa384a-b975-4678-bb33-7687468d44ce")
	public class TimeDurationMode extends AbstractMode<Long> {

		@Override
		protected Long getConfiguredRef() {
			return 2L;
		}

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("TimeDuration");
		}
	}
}