package i2iacademy;

import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.client.RetryLimitPolicy;
import org.apache.ignite.sql.ResultSet;
import org.apache.ignite.sql.SqlRow;
import java.util.Random;

public class IgniteClientApp {

    private static final String IGNITE_HOST = "127.0.0.1:10800";
    private static final Random random = new Random();

    public static void main(String[] args) {

        try (IgniteClient client = IgniteClient.builder()
                .addresses(IGNITE_HOST)
                .retryPolicy(new RetryLimitPolicy().retryLimit(5))
                .build()) {

            System.out.println("Apache Ignite bağlantısı kuruldu!");

            client.sql().execute(null,
                "CREATE TABLE IF NOT EXISTS Subscriber (" +
                "customerId VARCHAR PRIMARY KEY, " +
                "dataUsage DOUBLE, " +
                "smsUsage INT, " +
                "callUsage INT)");
            System.out.println("Subscriber tablosu oluşturuldu.");

            client.sql().execute(null, "DELETE FROM Subscriber");
            System.out.println("Tablo temizlendi.");

            System.out.println("\n5 Subscriber ekleniyor...");
            for (int i = 1; i <= 5; i++) {
                client.sql().execute(null,
                    "INSERT INTO Subscriber (customerId, dataUsage, smsUsage, callUsage) " +
                    "VALUES (?, ?, ?, ?)",
                    "CUSTOMER-" + i, 0.0, 0, 0);
                System.out.println("Eklendi: CUSTOMER-" + i);
            }

            System.out.println("\nKullanım verileri güncelleniyor...");
            try (ResultSet<SqlRow> rs = client.sql().execute(null,
                    "SELECT customerId FROM Subscriber")) {
                while (rs.hasNext()) {
                    SqlRow row = rs.next();
                    String customerId = row.stringValue("customerId");

                    double dataUsage = Math.round(random.nextDouble() * 10000.0) / 10.0;
                    int smsUsage = random.nextInt(500);
                    int callUsage = random.nextInt(300);

                    client.sql().execute(null,
                        "UPDATE Subscriber SET dataUsage=?, smsUsage=?, callUsage=? " +
                        "WHERE customerId=?",
                        dataUsage, smsUsage, callUsage, customerId);
                }
            }
            System.out.println("Kullanım verileri güncellendi.");

            System.out.println("\n--- Final Durum ---");
            try (ResultSet<SqlRow> rs = client.sql().execute(null,
                    "SELECT * FROM Subscriber ORDER BY customerId")) {
                while (rs.hasNext()) {
                    SqlRow row = rs.next();
                    Subscriber sub = new Subscriber(
                        row.stringValue("customerId"),
                        row.doubleValue("dataUsage"),
                        row.intValue("smsUsage"),
                        row.intValue("callUsage")
                    );
                    System.out.println(sub);
                }
            }

            System.out.println("\nProgram tamamlandı.");

        } catch (Exception e) {
            System.err.println("Hata: " + e.getMessage());
            e.printStackTrace();
        }
    }
}