package com.salesmanagementsystem.sales_management_system.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanagementsystem.sales_management_system.embbedables.Currency;
import com.salesmanagementsystem.sales_management_system.entities.PaymentSupplier;
import com.salesmanagementsystem.sales_management_system.entities.Supplier;
import com.salesmanagementsystem.sales_management_system.parameters.CreatePaymentSupplierParameters;
import com.salesmanagementsystem.sales_management_system.repositories.PaymentSupplierRepository;
import com.salesmanagementsystem.sales_management_system.repositories.SupplierRepository;
@Service
public class PaymentSupplierServiceImpl implements PaymentSupplierService {
    private final PaymentSupplierRepository paymentSupplierRepository;
    private final SupplierRepository supplierRepository;

    public PaymentSupplierServiceImpl(PaymentSupplierRepository paymentSupplierRepository, SupplierRepository supplierRepository) {
        this.paymentSupplierRepository = paymentSupplierRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public PaymentSupplier createPaymentSupplier(CreatePaymentSupplierParameters parameters) {
        PaymentSupplier paymentSupplier = new PaymentSupplier();
        Optional<Supplier> supplier = supplierRepository.findById(parameters.getSupplierId());
        if (!supplier.isEmpty()) {
            if (parameters.getCurrency() == Currency.SOLES) {
                supplier.get().decrementDebtSoles(parameters.getAmountPaid());
            } else {
                supplier.get().decrementDebtDollars(parameters.getAmountPaid());
            }
            supplierRepository.save(supplier.get());
            paymentSupplier.setAmountPaid(parameters.getAmountPaid());
            paymentSupplier.setCurrency(parameters.getCurrency());
            paymentSupplier.setPaymentMethod(parameters.getPaymentMethod());
            paymentSupplier.setNote(parameters.getNote());
            paymentSupplier.setSupplier(supplier.get());
            paymentSupplier.setPaymentCustomerStatus(true);
            paymentSupplier.setRegistrationDate(LocalDate.now());
            paymentSupplier.setLastUpdateDate(LocalDate.now());
        }
        return paymentSupplierRepository.save(paymentSupplier);
    }


}
