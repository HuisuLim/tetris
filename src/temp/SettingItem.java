package temp;

import java.util.List;

public class SettingItem {
    private String name;
    private List<String> options;
    private String currentValue;

    public SettingItem(String name, List<String> options, String defaultValue) {
        this.name = name;
        this.options = options;
        this.currentValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }
}
