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
    public void konstruktoriNegatiivisellaTilavuusArvollaTilavuusNolla(){  
    
    Varasto varasto = new Varasto(-1);    
    assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriNegatiivisellaTilavuusArvollaSaldoNolla() {
        
        Varasto varasto = new Varasto(-1);
        
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriNollalaTilavuusNolla() {
        
        Varasto varasto = new Varasto(0.0);
        
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriNollallaSaldoNolla() {
    
        Varasto varasto = new Varasto(0.0);
        
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriKahdellaNegatiivisellaArvollaTilavuusJaSaldoOnNolla() {
        
        Varasto varasto = new Varasto(-1,-1);
        
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriKahdellaNollallaTilavuusJaSaldoNolla() {
        
        Varasto varasto = new Varasto(0,0);
        
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriSaldoPienempiKuinTilavuusSaldoOikein() {
        
        Varasto varasto = new Varasto(100, 80);
        
        assertEquals(80, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(100, varasto.getTilavuus(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriAlkusaldoSuurempiKuinTilavuusAlkusaldoTilavuudeksi() {
        
        Varasto varasto = new Varasto(100, 120);
        
        assertEquals(100, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void getSaldoPositiivisellaArvollaTestaus() {
        
        Varasto varasto = new Varasto(100, 80);
        
        assertEquals(80, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void getSaldoNegatiivisellaArvollaTestaus() {
     
        Varasto varasto = new Varasto(100, -10);
        
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        
        
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
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNegatiivisellaParametrilla() {
        
        assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
        
    }
    
    @Test
    public void otaVarastostaSaldonVerranKunMaaraMitaOtetaanOnPienempiKuinSaldo() {
        
       Varasto varasto = new Varasto(10, 10);
       
       assertEquals(10.0, varasto.otaVarastosta(20), vertailuTarkkuus);
       
        
        
    }

    @Test
    public void otaVarastostaOikeaMaaraKunMaaraOnPienempiKuinSaldo() {
        
        Varasto varasto = new Varasto(10, 10);
        
        assertEquals(8, varasto.otaVarastosta(8), vertailuTarkkuus);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
        
    }
    
    @Test
    public void lisaaVarastoonVahemmanKuinNolla() {
        
        Varasto varasto = new Varasto(10, 5);
        
        varasto.lisaaVarastoon(-1);
        
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
        
        
    }
    
    @Test
    public void lisaaVarastoonKunLisattavaMaaraOnPienempiKuinPaljonkoMahtuu() {
    
        Varasto varasto = new Varasto (10,5);

        varasto.lisaaVarastoon(4.0);        
        
       assertEquals(9.0, varasto.getSaldo(), vertailuTarkkuus ); 
       
    }
    
    @Test
    public void lisaaVarastoonMaaraSuurempiKuinTilavuus() {
        
        Varasto varasto = new Varasto(10,5);
        
        varasto.lisaaVarastoon(10);
        
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
        
        
    }
    
    
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringMetodiOikein() {
        
        Varasto varasto = new Varasto(10, 8);
        
        assertEquals("saldo = " + 8.0 + ", vielä tilaa " + 2.0, varasto.toString());
        
    }
    
}