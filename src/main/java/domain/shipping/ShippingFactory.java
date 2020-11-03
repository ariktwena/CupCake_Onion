package domain.shipping;

import core.Shipping;
import infrastructure.LoginSampleException;

public interface ShippingFactory {
    Shipping createShipping(Shipping shipping) throws LoginSampleException;
}
