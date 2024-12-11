package ee.ege.veebipood.util;

// emaili / sms välja saatmine
// maksed mis on maksmata seisus
// andmebaasipuhastus
import ee.ege.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CronJobScheduler {

//    @Autowired
//    OrderRepository orderRepository;
//
//    // ? lõpus > vastavalt kellaajale
//    // @Scheduled(cron = "0 30 9 * * ?")
//    @Scheduled(cron = "0 * * * * *") // kui on * lõpus siis alatest käima panemise hetkest
//    public void runEveryMinute() {
//        Date date = new Date();
//        System.out.println(date.getMinutes() + ":" + date.getSeconds());
//    }
//
//  @Scheduled(cron = "0 50 9 * * MON-FRI")
//  public void sendBookingReminders() {
////      List<Order> orders = orderRepository.findAllByCreationBetween();
////      for (Order o: orders) {
////          o.getPerson().getEmail();
////      }
//      System.out.println("Tuli CRON 9:49");
//  }
//
//    @Scheduled(cron = "0/10 * * 6 * MON-FRI")
//    public void sendBookingReminders2() {
////      List<Order> orders = orderRepository.findAllByCreationBetween();
////      for (Order o: orders) {
////          o.getPerson().getEmail();
////      }
//        System.out.println("10 sek tagant");
//    }
}
