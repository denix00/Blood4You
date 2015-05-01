package tvz.trip.tvzmc2.blood4you;

import java.util.Date;


public class Lokacija {

    private String grad, adresa, radnoVrijeme;
    private Date datum;
    private Boolean izvanredna;
    public Lokacija(String grad, String adresa, Date datum, Boolean izvanredna, String radnoVrijeme)
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
    public Date getDatum() {   return datum;   }
    public Boolean getIzvanredna() {    return izvanredna;  }
}


