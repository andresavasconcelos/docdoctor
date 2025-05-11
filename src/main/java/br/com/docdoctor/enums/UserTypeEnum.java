package br.com.docdoctor.enums;

public enum UserTypeEnum {
    ADMIN(1L, "ADMIN"),
    VIWER(2L, "VIWER"),
    EDITOR(3L, "EDITOR");

    private Long id;
    private String description;


    UserTypeEnum(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
