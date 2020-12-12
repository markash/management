package threesixty.hr.core.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ValueChanges {
    
    private List<ValueChange> changes;

    public ValueChanges() {

        this.changes = new ArrayList<>();
    }

    public void add(final ValueChange valueChange) {

        if (valueChange != null) {

            this.changes.add(valueChange);
        }
    }

    public boolean isSame() {

        return this.changes.stream()
            .noneMatch(ValueChange::isDifferent);
    }

    public boolean isDifferent() {

        return this.changes.stream()
            .anyMatch(ValueChange::isDifferent);
    }

    public Stream<ValueChange> stream() {

        return this.changes.stream();
    }

    public Stream<ValueChange> differences() {

        return this.changes.stream().filter(ValueChange::isDifferent);
    }

    public List<ValueChange> getValueChanges() {

        return Collections.unmodifiableList(this.changes);
    }

    public boolean hasChanged(
        final String propertyName) {

        return differences()
                .anyMatch(vc -> vc.getPropertyName().equalsIgnoreCase(propertyName));
    }

}
