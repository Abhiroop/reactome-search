import java.util.List;

public class Page 
{

		final int count;
        final List<Record> records;

        public Page(int count, List<Record> records) {
            this.count = count;
            this.records = records;
        }

        public int getCount() {
            return count;
        }

        public List<Record> getRecords() {
            return records;
        }

 }