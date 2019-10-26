package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

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
    public void negativeCapasityResultsInZeroVarasto(){
        Varasto var = new Varasto(-10);
        assertEquals(0.0, var.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void initialCapasityWithItemsIsReturnedCorrectly(){
        Varasto var = new Varasto(10, 5);
        assertEquals(10.0, var.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void initialNegativeCapasityWithItemsIsReturnedCorrectly(){
        Varasto var = new Varasto(-10, 5);
        assertEquals(0.0, var.getTilavuus(), 0.0);
    }

    @Test
    public void initialNegativeItemsIsReturnedCorrectly(){
        Varasto var = new Varasto(10, -5);
        assertEquals(0.0, var.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void addingNegativeAmountDoesNothing() {
        varasto.lisaaVarastoon(-5);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void addingMoreThanCapasityFillsUpVarasto() {
        varasto.lisaaVarastoon(15);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
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
    public void gettingNegativeFromVarastoReturnsZeroPointZero(){
        assertEquals(0.0, varasto.otaVarastosta(-10.0), vertailuTarkkuus);
    }

    @Test
    public void gettingMoreThanCurrentAmountOfItemsEmptiesVarasto(){
        varasto.lisaaVarastoon(5.0);
        double fromVarasto = varasto.otaVarastosta(10.0);
        assertEquals(5.0, fromVarasto, vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10.0, varasto.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void toStringReturnsCorrectString(){
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
        varasto.lisaaVarastoon(5.0);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

}