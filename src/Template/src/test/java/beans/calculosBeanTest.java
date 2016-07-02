/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author grupo3
 */
public class calculosBeanTest {
    
    private calculosBean calBean;
    
    public calculosBeanTest() {   
        calBean = new calculosBean();        
    }
    
    @Test
    public void testCalcularMulta() { 
        System.out.println("testCalcularMulta:");
        
        LocalDate localDateHoje = LocalDate.now(); 
        Date dateTestCalcularMulta = new GregorianCalendar(2016, Calendar.JUNE, 28).getTime();
        LocalDate localDateTestCalcularMulta = new java.sql.Date(dateTestCalcularMulta.getTime()).toLocalDate();
        
        float multa = (float) (localDateTestCalcularMulta.until(localDateHoje, ChronoUnit.DAYS) * 85.00);
        
        assertEquals("R$ " + multa, calBean.calcularMulta(dateTestCalcularMulta));
        
        System.out.println("");
    }

    @Test
    public void testCalcularTempoRestante1() {
        System.out.println("testCalcularTempoRestante1:");
        
        Date dateHoje = new Date();
        Date dateAmanha = new Date(dateHoje.getTime() + (1000 * 60 * 60 * 24));
        
        assertEquals("0 dias, 23 horas, 59 minutos e 59 segundos.", calBean.calcularTempoRestante(dateAmanha));
        
        System.out.println("");
    }

    @Test
    public void testCalcularTempoRestante2() {
        System.out.println("testCalcularTempoRestante2:");
        
        Date dateHoje = new Date();
        Date dateOntem = new Date(dateHoje.getTime() - (1000 * 60 * 60 * 24));
        
        System.out.println("Aluguel Atrasado");
        
        assertEquals("Aluguel Atrasado", calBean.calcularTempoRestante(dateOntem));
        
        System.out.println("");
    }

    @Test
    public void testCalcularHorarioFuncionamento() {
        System.out.println("testCalcularHorarioFuncionamento:");
        
        Calendar calendarHoje = Calendar.getInstance();
        int hora = calendarHoje.get(Calendar.HOUR_OF_DAY);
        int minuto = calendarHoje.get(Calendar.MINUTE);
        int segundo = calendarHoje.get(Calendar.SECOND);
        int milisegundo = calendarHoje.get(Calendar.MILLISECOND);
        
        if(hora >= 10 && hora < 19) {
            System.out.println("VerificaÃ§Ã£o 1: (Aberto)");
            assertEquals("(Aberto)", calBean.calcularHorarioFuncionamento());
        } else if (hora == 20 && minuto == 0 && segundo == 0 && milisegundo == 0) {
            System.out.println("VerificaÃ§Ã£o 2: (Aberto)");
            assertEquals("(Aberto)", calBean.calcularHorarioFuncionamento());
        } else {
            System.out.println("VerificaÃ§Ã£o 3: (Fechado)");
            assertEquals("(Fechado)", calBean.calcularHorarioFuncionamento());
        }   
        
        System.out.println("");
    }
    
}
