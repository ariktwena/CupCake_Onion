package domain.user;

import core.User;
import infrastructure.LoginSampleException;

public interface UserServices {

    void updateUserById(int user_id, String user_role, double user_credit) throws LoginSampleException;

    void deleteUserById(int user_id) throws LoginSampleException;

    void changeUserRoleToCustomer(int user_id) throws LoginSampleException;

    void changeUserRoleToAdmin(int user_id) throws LoginSampleException;

    boolean userAldreadyExistsInDB(String email);

    User login(String user_email ) throws LoginSampleException;

    void updateUserCreditBalance(int user_id, double newCreditBalance) throws LoginSampleException;

}
