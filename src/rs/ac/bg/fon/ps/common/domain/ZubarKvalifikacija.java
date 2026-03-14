package rs.ac.bg.fon.ps.common.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ZubarKvalifikacija implements GenericEntity {

    private Zubar zubar;
    private Kvalifikacija kvalifikacija;
    private Date datumSticanja;

    public ZubarKvalifikacija() {
    }

    public ZubarKvalifikacija(Zubar zubar, Kvalifikacija kvalifikacija, Date datumSticanja) {
        this.zubar = zubar;
        this.kvalifikacija = kvalifikacija;
        this.datumSticanja = datumSticanja;
    }

    public Zubar getZubar() {
        return zubar;
    }

    public void setZubar(Zubar zubar) {
        this.zubar = zubar;
    }

    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    @Override
    public String getTableName() {
        return "zubar_kvalifikacija";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "id_zubar, id_kvalifikacija, datum_sticanja";
    }

    @Override
    public String getInsertValues() {
        return zubar.getZubarId() + ", " +
               kvalifikacija.getKvalifikacijaId() + ", '" +
               datumSticanja + "'";
    }

    @Override
    public void setId(Long id) {
        // nema auto ID jer je kompozitni ključ
    }

    @Override
    public List<GenericEntity> getListFromResultSet(ResultSet rs) throws Exception {

        List<GenericEntity> lista = new ArrayList<>();

        while (rs.next()) {

            Long zubarId = rs.getLong("id_zubar");
            String ime = rs.getString("zubar_ime");
            String prezime = rs.getString("zubar_prezime");

            Long kvalifikacijaId = rs.getLong("id_kvalifikacija");
            String naziv = rs.getString("kvalifikacija_naziv");

            Date datum = rs.getDate("datum_sticanja");

            Zubar z = new Zubar();
            z.setZubarId(zubarId);
            z.setIme(ime);
            z.setPrezime(prezime);

            Kvalifikacija k = new Kvalifikacija();
            k.setKvalifikacijaId(kvalifikacijaId);
            k.setNaziv(naziv);

            ZubarKvalifikacija zk = new ZubarKvalifikacija(z, k, datum);

            lista.add(zk);
        }

        return lista;
    }

    @Override
    public String getPrimaryKeyColumnName() {
        return "id_zubar";
    }

    @Override
    public Long getPrimaryKeyValue() {
        return zubar.getZubarId();
    }

    @Override
    public String getUpdateSetClause() {
        return "datum_sticanja='" + datumSticanja + "'";
    }

    @Override
    public String getWhereCondition() {
        return "id_zubar = " + zubar.getZubarId() + " AND id_kvalifikacija = " + kvalifikacija.getKvalifikacijaId();
    }

}