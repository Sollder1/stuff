package de.sollder1.stuff;


import de.sollder1.stuff.jsonparser.JsonParser;
import de.sollder1.stuff.jsonparser.token.Tokenizer;

public class Main {

    public static void main(String[] args) {


        String testJson = """
                [{"id":"53590461-f322-4777-a724-299be1225a1f","createdBy":null,"lastUpdatedBy"
                :null,"createdAt":1641158586106,"lastUpdatedAt":1641158586106,"name":"Investment"},
                {"id":"d22f39dc-9fbe-4c60-992a-281302922371","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641158619839,"lastUpdatedAt":1641158619839,"name":"Wohnen"},
                {"id":"663217ef-3097-4715-8c93-55238e1f802a","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641158703359,"lastUpdatedAt":1641158703359,"name":"Abos"},
                {"id":"40e51460-05ef-41af-b0a2-e4eb470af37e","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641159046180,"lastUpdatedAt":1641159046180,"name":"Versicherungen"},
                {"id":"b1124c8d-b329-4cce-8fd5-d60da725df72","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641203748901,"lastUpdatedAt":1641203748901,"name":"Essen/Trinken"},
                {"id":"c295d59a-7611-446e-9e36-95e85f4571ce","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641380046223,"lastUpdatedAt":1641380046223,"name":"Telekommunikation"},
                {"id":"17f9cc99-2e82-4c4e-94dd-bfe2b91300dd","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641668976534,"lastUpdatedAt":1641668976534,"name":"Auto"},
                {"id":"9386d2ab-899a-4de5-b092-7df4ebc6fbe4","createdBy":null,"lastUpdatedBy":null,
                "createdAt":1641159252436,"lastUpdatedAt":1641159252436,"name":"Gehalt"}]
                """;

        String testJson2 = """
                {
                  "status": {
                    "code": 200,
                    "description": "User retrieved successfully."
                  },
                  "payload": {
                    "user": {
                      "firstName": "Joe",
                      "lastName": "Doe",
                      "role": 3,
                      "email": "doe@example.com",
                      "customerID": "",
                      "projects": [
                      "6IXG5mEg6QLl9rhVSE6m",
                      "Hs1bHiOIqKclwwis3CNf",
                      "8C2OUGVZXU35FA7iwRn4"
                      ],
                      "status": "Status",
                      "id": "c1BSZnKLdHSRYqrU5hqiQo733j13"
                    }
                  }
                }
                """;

        JsonParser parser = new JsonParser();

        parser.readTree(testJson2);
        var tree = parser.readTree(testJson2);

        var res = parser.writeTree(tree);

        System.out.println(res);

        parser.readTree(res);


    }

}
