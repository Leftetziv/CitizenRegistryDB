import java.util.Objects;

public class Politis {

    private String arithmosTautotitas;
    private String onoma;
    private String eponymo;
    private String fylo;
    private String dob;
    private String afm;
    private String address;

    public Politis(String arithmosTautotitas, String onoma, String eponymo, String fylo, String dob, String afm, String address) {
        this.arithmosTautotitas = arithmosTautotitas;
        this.onoma = onoma;
        this.eponymo = eponymo;
        this.fylo = fylo;
        this.dob = dob;
        this.afm = afm;
        this.address = address;
    }

    public String getArithmosTautotitas() {
        return arithmosTautotitas;
    }

    public void setArithmosTautotitas(String arithmosTautotitas) {
        this.arithmosTautotitas = arithmosTautotitas;
    }

    public String getOnoma() {
        return onoma;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public String getEponymo() {
        return eponymo;
    }

    public void setEponymo(String eponymo) {
        this.eponymo = eponymo;
    }

    public String getFylo() {
        return fylo;
    }

    public void setFylo(String fylo) {
        this.fylo = fylo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politis politis = (Politis) o;
        return Objects.equals(arithmosTautotitas, politis.arithmosTautotitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arithmosTautotitas);
    }

    @Override
    public String toString() {
        return "Politis{" +
                "arithmosTautotitas='" + arithmosTautotitas + '\'' +
                ", onoma='" + onoma + '\'' +
                ", eponymo='" + eponymo + '\'' +
                ", fylo='" + fylo + '\'' +
                ", dob='" + dob + '\'' +
                ", afm=" + afm +
                ", address='" + address + '\'' +
                '}';
    }
}
