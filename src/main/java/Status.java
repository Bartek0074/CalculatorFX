import java.util.Objects;

public class Status {

    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status aCase = (Status) o;
        return Objects.equals(status, aCase.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
