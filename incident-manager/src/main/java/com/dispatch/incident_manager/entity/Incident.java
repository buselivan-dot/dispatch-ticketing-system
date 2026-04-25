public class Incident{
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Long solverId;

    public Incident(Long id, String title, String description, String status, String priority, Long solverId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.solverId = solverId;
    }

    public Long getId() {return this.id;}
    public String getTitle() {return this.title;}
    public String getDescription() {return this.description;}
    public String getStatus() {return this.status;}
    public String getPriority() {return this.priority;}
    public Long getSolverId() {return this.solverId;}

    public void setId(Long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setStatus(String status) {this.status = status;}
    public void setPriority(String priority) {this.priority = priority;}
    public void setSolverId(Long solverId){this.solverId = solverId;}




}