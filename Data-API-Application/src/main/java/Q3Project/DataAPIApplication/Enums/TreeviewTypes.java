package Q3Project.DataAPIApplication.Enums;

public enum TreeviewTypes {
    MACHINE(16),
    COMPONENT(2),
    ;

    public final int treeviewSoortId;

    TreeviewTypes(int treeviewSoortId) {
        this.treeviewSoortId = treeviewSoortId;
    }
}
