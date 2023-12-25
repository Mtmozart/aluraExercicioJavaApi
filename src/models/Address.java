package models;

public class Address {
    private int cep;
    private String street;
   private String neighborhood;
    private String city;
    private String uf;

    public Address(int cep, String street, String neighborhood, String city, String uf) {
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
    }

    public Address (AdressViaCep adressViaCep){
        var cep = adressViaCep.cep().replace("-", "");
        this.cep = Integer.valueOf(cep);
        this.street = adressViaCep.logradouro();
        this.neighborhood = adressViaCep.bairro();
        this.city = adressViaCep.localidade();
        this.uf = adressViaCep.uf();

    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cep=" + cep +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", estado= " + uf +
                '}';
    }

}
