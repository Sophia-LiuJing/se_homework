package cn.wzw.domain;



public class FileUrl {
    private Long id;
    private String url;

    public FileUrl() {
    }

    @Override
    public String toString() {
        return "FileUrl{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }

    public FileUrl(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
