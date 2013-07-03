    public class Record {

        final String id;
        final String name;
        final String description;

        public Record(String id, String name, String description) {
            this.id          = id;
            this.name        = name;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

    }