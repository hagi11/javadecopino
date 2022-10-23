package controlador;

import java.util.function.Function;

public class CtrContrasena {

    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(12);

    public String hash(String password) {
        return password;
//        return bcrypt.hash(password);
    }

//    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
//        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
//    }
}
