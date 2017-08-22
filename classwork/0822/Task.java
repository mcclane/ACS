//https://codeshare.io/aV3xz9

public class Task
{
  private String task;
  private int rank;
  public Task(String task, int rank) {
    this.task = task;
    this.rank = rank;
  }
  public String getTask() {
    return task;
  }
  public int getRank() {
    return rank;
  }
  public String toString() {
    return rank+" "+task;
  }
}