package demo.ui.constants.menuHeaders;

public enum headingName {

    INPUT_FORMS("Input Forms"),
    DATE_PICKER("Date pickers"),
    TABLE("Table"),
    DEMO_HOME("Demo Home"),
    PROGRESS_BAR("Progress Bars"),
    ALERT_MODALS("Alerts & Modals"),
    LIST_BOX("List Box"),
    OTHERS("Others");


    private final String fieldName;

    headingName(String fieldName) {
        this.fieldName = fieldName;

    }

    public String getFieldName() {
        return fieldName;
    }


}
