package ua.nure.matchenko.practice7.constants;

public enum XML {
    STEEL_ARM("SteelArm"), KNIFE("Knife"), TYPE("Type"), HANDY("Handy"),
    ORIGIN("Origin"), VISUAL("Visual"), BLADE("Blade"), LENGTH("Length"),
    WIDTH("Width"), MATERIAL("Material"), BLADE_MATERIAL("BladeMaterial"),
    HANDLE("Handle"), WOOD_HANDLE("WoodHandle"), WOOD_TYPE("WoodType"),
    BLOODSTREAM("Bloodstream"), VALUE("Value");

    private String value;

    XML(String value) {
        this.value = value.intern();
    }

    /*public boolean equalsTo(String name) {
        return value.equals(name);
    }*/

    public String value() {
        return value;
    }
}
