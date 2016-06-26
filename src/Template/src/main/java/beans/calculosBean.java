package beans;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class calculosBean {

    //LocalDate to Date: http://stackoverflow.com/questions/33066904/localdate-to-java-util-date-and-vice-versa-simpliest-conversion
    //java.time API: http://blog.caelum.com.br/conheca-a-nova-api-de-datas-do-java-8/
    public String calcularMulta(Date data_dev) {
        LocalDate data_dev_aux = new java.sql.Date(data_dev.getTime()).toLocalDate();
        LocalDate today = LocalDate.now();

        //Verifica se o periodo de devoluçao nao foi excedido.
        if (data_dev_aux.isBefore(today)) {
            float multa = (float) (data_dev_aux.until(today, ChronoUnit.DAYS) * 85.00);
            System.out.println("Multa: " + multa);

            return "R$ " + multa;
        } else {
            System.out.println("Período de devolução não excedido.");
        }

        return "-";

    }

    public String calcularTempoRestante(Date data_dev) {
        LocalDate data_dev_aux = new java.sql.Date(data_dev.getTime()).toLocalDate();
        LocalDate todayAux = LocalDate.now();
        Date today = new Date();

        //Verifica se o periodo de devoluçao nao foi excedido.
        if (todayAux.isBefore(data_dev_aux)) {
            long different = data_dev.getTime() - today.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            System.out.println(elapsedDays + " dias, " + elapsedHours + " horas, " + elapsedMinutes + " minutos e " + elapsedSeconds + " segundos.");

            return elapsedDays + " dias, " + elapsedHours + " horas, " + elapsedMinutes + " minutos e " + elapsedSeconds + " segundos.";
        }
        
        return "Aluguel Atrasado";
    }

    //Retorna "(Aberto)"/"(Fechado)" caso o estabelecimento estja Aberta/Fechado.
    //Horário de Funcionamento: Todos os dias ás 10:00h ás 20:00h.
    public String calcularHorarioFuncionamento() {
        Calendar abre = Calendar.getInstance();
        abre.set(Calendar.HOUR_OF_DAY, 10);
        abre.set(Calendar.MINUTE, 0);
        abre.set(Calendar.SECOND, 0);
        abre.set(Calendar.MILLISECOND, 0);

        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.HOUR_OF_DAY, 20);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);

        Calendar hoje = Calendar.getInstance();

        //Verifica se o periodo de devoluçao nao foi excedido.
        if (abre.compareTo(hoje) < 0 && fecha.compareTo(hoje) > 0) {
            System.out.println("Estabelecimento está Aberto.");

            return "(Aberto)";
        }

        System.out.println("Estabelecimento está Fechado.");
        return "(Fechado)";
    }
}
