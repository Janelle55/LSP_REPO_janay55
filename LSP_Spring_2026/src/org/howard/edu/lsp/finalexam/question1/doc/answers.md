Part 1:

Shared Resource #1:
nextId

Shared Resource #2:
requests list

Concurrency Problem:
Race condition

Why addRequest() is unsafe:
Multiple threads can call getNextId() at the same time, causing duplicate IDs because nextId++ is not atomic. Also, multiple threads can modify the requests list at the same time, which can lead to inconsistent or corrupted data.

Part 2:

Fix A: Explanation
Correct. Synchronizing getNextId() ensures that only one thread can access and update nextId at a time, preventing duplicate IDs.

Fix B: Explanation
Correct. Synchronizing addRequest() ensures that both ID generation and adding to the list happen together safely, preventing race conditions on both nextId and requests.

Fix C: Explanation
Not correct. Synchronizing getRequests() does not protect nextId or the requests list during modification. Race conditions can still occur when adding requests.

Part 3:

Answer + Explanation
No, getNextId() should not be public. It exposes internal behavior and breaks encapsulation. According to Riel’s heuristics, methods should hide implementation details and only expose necessary functionality.

Part 4:

Description:
An alternative approach is to use thread-safe classes such as AtomicInteger for ID generation and a thread-safe list like CopyOnWriteArrayList. This avoids using synchronized while still ensuring safe concurrent access.

Code Snippet:
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private List<String> requests = new CopyOnWriteArrayList<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    }
}