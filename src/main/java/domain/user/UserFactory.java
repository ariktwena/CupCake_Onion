package domain.user;

import core.User;
import infrastructure.LoginSampleException;

public interface UserFactory {

    User createUser(User user) throws LoginSampleException;


}
