package Resources;

public enum EnumClass {
    GetEmp ("/api/users?page=2"),
    AddEmp ("/api/users"),
    UpdateEmp ("/api/users/2"),
    DeleteEmp ("/api/users/2");

    private String URL;

    EnumClass(String s) {
    }

    void URLMethod(String URL)
    {
        this.URL=URL;
    }
    
    public String getURL()
    {
        return URL;
    }
}
    
