package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luodessaSaldoLisataan() {
        Varasto testiVarasto = new Varasto(10, 5);

        assertEquals(5.0, testiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void voidaanLaittaaVainMaksimiTilavuus() {

        varasto.lisaaVarastoon(100);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianPieniTilavuusKorjataanNollaan() {
        Varasto virheellinen = new Varasto(0);

        assertEquals(0.0, virheellinen.getTilavuus(), vertailuTarkkuus);

        Varasto negatiivinenVarasto = new Varasto(-0.1);

        assertEquals(0.0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void liianPieniTilavuusKorjataanNollaan2() {
        Varasto virheellinen = new Varasto(0.0, 0.0);

        assertEquals(0.0, virheellinen.getTilavuus(), vertailuTarkkuus);

        Varasto negatiivinenVarasto = new Varasto(-0.1, 0.0);

        assertEquals(0.0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void varastonToStringToimii() {

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void otetaanNegatiivinenMaara() {

        assertEquals(0.0, varasto.otaVarastosta(-5.0), vertailuTarkkuus);
    }

    @Test
    public void ottaessaEnemmanKuinOnAnnetaanKaikki() {
        varasto.lisaaVarastoon(5);

        assertEquals(5, varasto.otaVarastosta(6), vertailuTarkkuus);
    }

    @Test
    public void lisataanNegatiivinenMaara() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);

        assertEquals(5.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisataanEnemmanKuinMahtuu() {
        varasto.lisaaVarastoon(200);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiLuodaNegatiivisena() {
        Varasto virheellinen = new Varasto(-5);

        assertEquals(0.0, virheellinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonSaldoEivoiOllaNegatiivinenLuodessa() {
        Varasto virheellinen = new Varasto(10, -5);

        assertEquals(0.0, virheellinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoaLuodessaTilaaEiYlikuormiteta() {
        Varasto virheellinen = new Varasto(10, 20);

        assertEquals(10, virheellinen.getSaldo(), vertailuTarkkuus);
    }

}