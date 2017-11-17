package github.tornaco.xposedmoduletest.xposed.service;

import java.util.concurrent.ExecutorService;

/**
 * Created by guohao4 on 2017/11/16.
 * Email: Tornaco@163.com
 */

class AsyncTrying {

    public interface Once {
        boolean once();
    }

    static void tryTillSuccess(final ExecutorService service, final Once once) {
        tryTillSuccess(service, once, null);
    }

    static void tryTillSuccess(final ExecutorService service, final Once once, final Runnable onSuccess) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                if (!once.once()) {
                    service.execute(this);
                    return;
                }
                if (onSuccess != null) onSuccess.run();
            }
        });
    }
}