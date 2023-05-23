package uk.ncl.CSC8016.jackbergus.coursework.project2.processes;

import uk.ncl.CSC8016.jackbergus.coursework.project2.utils.Item;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ProductMonitor {

    private final Lock lock1;
    private Lock lock2;

    Queue<Item> available;
    Queue<Item> withdrawn;

    public ProductMonitor() {
        available = new LinkedList<>();
        withdrawn = new LinkedList<>();
        lock1 = new ReentrantLock();
        lock2 = new ReentrantLock();
    }

    public void removeItemsFromUnavailability(Collection<Item> cls) {
        try {
            RainforestShop.acquireLocks(lock1, lock2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Item x : cls) {
                if (withdrawn.remove(x))
                    available.add(x);
            }
        lock1.unlock();
        lock2.unlock();
        }

    //removes an item from available and adds it to withdrawn while retuning that item
    public Optional<Item> getAvailableItem() {
        try {
            RainforestShop.acquireLocks(lock1, lock2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Optional<Item> o = Optional.empty();
        if (!available.isEmpty()) {
            var obj = available.remove();
            if (obj != null) {
                o = Optional.of(obj);
                withdrawn.add(o.get());
            }
        }
        lock1.unlock();
        lock2.unlock();
        return o;
    }

    // shelves an item removing it from withdrawn while adding it to available
    public boolean doShelf(Item u) {
        try {
            RainforestShop.acquireLocks(lock1, lock2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean result = false;
        if (withdrawn.remove(u)) {
            available.add(u);
            result = true;
        }
        lock1.unlock();
        lock2.unlock();
        return result;
    }
    //returns a set of the available item names
    public Set<String> getAvailableItems() {
        synchronized (lock1) {
            Set<String> s;
            s = available.stream().map(x -> x.productName).collect(Collectors.toSet());
            return s;
        }
    }

    public void addAvailableProduct(Item x) {
        synchronized (lock1) {
            available.add(x);
        }
    }

    public double updatePurchase(Double aDouble,
                                 List<Item> toIterate,
                                 List<Item> currentlyPurchasable,
                                 List<Item> currentlyUnavailable) {
        synchronized (lock2) {
            double total_cost = 0.0;
            for (var x : toIterate) {
                if (withdrawn.contains(x)) {
                    currentlyPurchasable.add(x);
                    total_cost += aDouble;
                } else {
                    currentlyUnavailable.add(x);
                }
            }
            return total_cost;
        }
    }

    public void makeAvailable(List<Item> toIterate) {
        try {
            RainforestShop.acquireLocks(lock1, lock2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (var x : toIterate) {
            if (withdrawn.remove(x)) {
                available.add(x);
            }
        }
        lock1.unlock();
        lock2.unlock();
    }

    public boolean completelyRemove(List<Item> toIterate) {
        try {
            RainforestShop.acquireLocks(lock1, lock2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean allEmpty;
        for (var x : toIterate) {
            withdrawn.remove(x);
            available.remove(x);
        }
        allEmpty = withdrawn.isEmpty() && available.isEmpty();
        lock1.unlock();
        lock2.unlock();

        return allEmpty;
    }
}
