package rs.ac.bg.fon.ps.common.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Usluga implements GenericEntity {

    private Long uslugaId;
    private String naziv;
    private double ukupanIznos;
    private double popust;
    private double ukupanIznosSaPopustom;

    private Zubar zubar;
    private Klijent klijent;

    private List<StavkaUsluge> stavke;

    public Usluga() {
        stavke = new ArrayList<>();
    }

    public Usluga(Long uslugaId, String naziv, double ukupanIznos, double popust,
                  double ukupanIznosSaPopustom, Zubar zubar, Klijent klijent) {
        this.uslugaId = uslugaId;
        this.naziv = naziv;
        this.ukupanIznos = ukupanIznos;
        this.popust = popust;
        this.ukupanIznosSaPopustom = ukupanIznosSaPopustom;
        this.zubar = zubar;
        this.klijent = klijent;
        this.stavke = new ArrayList<>();
    }

    public Long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(Long uslugaId) {
        this.uslugaId = uslugaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getUkupanIznosSaPopustom() {
        return ukupanIznosSaPopustom;
    }

    public void setUkupanIznosSaPopustom(double ukupanIznosSaPopustom) {
        this.ukupanIznosSaPopustom = ukupanIznosSaPopustom;
    }

    public Zubar getZubar() {
        return zubar;
    }

    public void setZubar(Zubar zubar) {
        this.zubar = zubar;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaUsluge> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaUsluge> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String getTableName() {
        return "usluga";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "naziv, ukupan_iznos, popust, ukupan_iznos_sa_popustom, id_zubar, id_klijent";
    }

    @Override
    public String getInsertValues() {
        return "'" + naziv + "', " +
                ukupanIznos + ", " +
                popust + ", " +
                ukupanIznosSaPopustom + ", " +
                zubar.getZubarId() + ", " +
                klijent.getKlijentId();
    }

    @Override
    public void setId(Long id) {
        this.uslugaId = id;
    }

    @Override
    public List<GenericEntity> getListFromResultSet(ResultSet rs) throws Exception {

        List<GenericEntity> lista = new ArrayList<>();

        while (rs.next()) {

            Long id = rs.getLong("id_usluga");
            String naziv = rs.getString("naziv");
            double ukupan = rs.getDouble("ukupan_iznos");
            double popust = rs.getDouble("popust");
            double ukupanSaPopustom = rs.getDouble("ukupan_iznos_sa_popustom");

            Long zubarId = rs.getLong("id_zubar");
            String zubarIme = rs.getString("zubar_ime");
            String zubarPrezime = rs.getString("zubar_prezime");

            Long klijentId = rs.getLong("id_klijent");
            String klijentIme = rs.getString("klijent_ime");
            String klijentPrezime = rs.getString("klijent_prezime");

            Zubar z = new Zubar();
            z.setZubarId(zubarId);
            z.setIme(zubarIme);
            z.setPrezime(zubarPrezime);

            Klijent k = new Klijent();
            k.setKlijentId(klijentId);
            k.setIme(klijentIme);
            k.setPrezime(klijentPrezime);

            Usluga u = new Usluga(id, naziv, ukupan, popust, ukupanSaPopustom, z, k);

            lista.add(u);
        }

        return lista;
    }

    @Override
    public String getPrimaryKeyColumnName() {
        return "id_usluga";
    }

    @Override
    public Long getPrimaryKeyValue() {
        return uslugaId;
    }

    @Override
    public String getUpdateSetClause() {
        return "naziv='" + naziv +
                "', ukupan_iznos=" + ukupanIznos +
                ", popust=" + popust +
                ", ukupan_iznos_sa_popustom=" + ukupanIznosSaPopustom +
                ", id_zubar=" + zubar.getZubarId() +
                ", id_klijent=" + klijent.getKlijentId();
    }

    @Override
    public String toString() {
        return naziv;
    }
}