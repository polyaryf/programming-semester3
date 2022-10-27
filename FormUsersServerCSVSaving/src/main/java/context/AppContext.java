package context;

import dao.UsersRepository;
import dao.impl.UsersRepositoryFileImpl;
import dao.impl.UsersRepositoryTempImpl;

public class AppContext {
    public static UsersRepository usersRepository = new UsersRepositoryFileImpl();

}

