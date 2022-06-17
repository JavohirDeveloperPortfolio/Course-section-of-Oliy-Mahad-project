package uz.oliymahad.courseservice.entity.quequeue;


public enum Status {
    PENDING(1),
    ACCEPT(2),
    REJECT(3),
    CANCEL(4),
    COMPLETED(5);

    int val;
    Status(int val) {
        this.val = val;
    }
}
