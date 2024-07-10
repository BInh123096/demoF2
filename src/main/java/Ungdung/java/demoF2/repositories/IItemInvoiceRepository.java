package Ungdung.java.demoF2.repositories;
import Ungdung.java.demoF2.entities.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IItemInvoiceRepository extends JpaRepository<ItemInvoice, Long>{
}
