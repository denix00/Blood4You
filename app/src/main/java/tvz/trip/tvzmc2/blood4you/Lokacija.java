package tvz.trip.tvzmc2.blood4you;


//klasa koja se koristi za spremanje liste lokacija u ListActivity.java i SpecialAdapter.java


public class Lokacija {

    private String grad, adresa, radnoVrijeme, datum;
//    private Date datum;
    private Boolean izvanredna;
    public Lokacija(String grad, String adresa, String datum, Boolean izvanredna, String radnoVrijeme)
    {
        this.grad = grad;
        this.adresa = adresa;
        this.datum = datum;
        this.izvanredna = izvanredna;
        this.radnoVrijeme = radnoVrijeme;
    }

    public String getGrad() {  return grad; }
    public String getAdresa() {  return adresa; }
    public String getRadnoVrijeme() {  return radnoVrijeme; }
    public String getDatum() {   return datum;   }
    public Boolean getIzvanredna() {    return izvanredna;  }
}


