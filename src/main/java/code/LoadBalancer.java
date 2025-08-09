package code;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//Thread safe Load balancer, that allows registering 10 servers at max
public class LoadBalancer {

    private final List<String> servers = new CopyOnWriteArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private int currentIndex = 0;

    // Register a new server
    public void registerServer(String server) {
        if(servers.size() == 10) {
            throw new IllegalArgumentException("LB running at max capacity");
        }
        servers.add(server);
    }

    // Unregister an existing server
    public void unregisterServer(String server) {
        servers.remove(server);
    }

    // Get the next server in round-robin fashion
    public String getNextServer() {
	/*can be a separate interface for different strategies
	eg. Random server strategy:
	int index = (int) (Math.random() * list.size());
	return list.get(index);
	*/
        try {
            lock.tryLock(1, TimeUnit.SECONDS);

            if (servers.isEmpty()) {
                throw new IllegalStateException("No servers available");
            }
            String server = servers.get(currentIndex);
            currentIndex = (currentIndex + 1) % servers.size();
            return server;
        } catch (InterruptedException e) {
            throw new IllegalStateException("Unable to serve");
        } finally {
            lock.unlock();
        }
    }
}
