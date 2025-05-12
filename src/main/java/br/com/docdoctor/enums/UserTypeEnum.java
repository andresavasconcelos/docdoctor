package br.com.docdoctor.enums;

public enum UserTypeEnum {
    ADMIN(1L, "ADMIN"),
    VIEWER(2L, "VIEWER"),
    EDITOR(3L, "EDITOR");

    private final Long id;
    private final String description;

    UserTypeEnum(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static UserTypeEnum fromId(Long id) {
        if (id == null) {
            return null;
        }
        for (UserTypeEnum type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + id);
    }
}