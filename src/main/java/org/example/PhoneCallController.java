package org.example;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PhoneCallController {

    public static final List<PhoneCall> PHONE_CALLS = List.of(
            new PhoneCall(1, "ИТМ", LocalDate.now().minusDays(2), 1, 1),
            new PhoneCall(1, "ВТМ", LocalDate.now().minusDays(1), 3, 2),
            new PhoneCall(1, "АП", LocalDate.now().minusDays(5), 2, 2),
            new PhoneCall(1, "АП", LocalDate.now().minusDays(4), 1, 2),
            new PhoneCall(1, "ИТМ", LocalDate.now().minusDays(3), 2, 1)
    );

    public static final List<Customer> CUSTOMERS = List.of(
            new Customer(1, "Иван", "Физик"),
            new Customer(2, "Пётр", null),
            new Customer(3, "ООО 'ФИРМА'", "Юрик")
    );

    public static final List<Manager> MANAGERS = List.of(
            new Manager(1, "Иван", "Внутренний"),
            new Manager(2, "Пётр", "Партнёр")
    );

    @QueryMapping
    public List<PhoneCall> phoneCalls(@Argument int number) {
        return PHONE_CALLS.subList(0, number);
    }

    @SchemaMapping(typeName = "PhoneCall", field = "customer")
    public Customer customer(PhoneCall phoneCall) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.getId() == phoneCall.getCustomer())
                .findFirst()
                .orElse(null);
    }

    @SchemaMapping(typeName = "PhoneCall", field = "manager")
    public Manager manager(PhoneCall phoneCall) {
        return MANAGERS.stream()
                .filter(manager -> manager.getId() == phoneCall.getManager())
                .findFirst()
                .orElse(null);
    }
}
