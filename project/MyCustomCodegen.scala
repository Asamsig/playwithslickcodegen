import slick.codegen.SourceCodeGenerator
import slick.model.Model

class MyCustomCodegen(model: Model) extends SourceCodeGenerator(model) {

  override val ddlEnabled = false

  override def tableName  = (dbName: String) => dbName.toCamelCase + "Table"
  override def entityName = (dbName: String) => dbName.toCamelCase

  override def code = "import play.api.libs.json._\n" + super.code

  override def Table = new Table(_) {
    override def factory: String = s"(${TableClass.elementType}.apply _).tupled"
    override def EntityType = new EntityType {
      override def code: String =
        super.code + s"\nobject $rawName {\n  implicit val ${name}Format = Json.format[$rawName]\n}"
    }
    override def PlainSqlMapper = new PlainSqlMapper {
      override def enabled: Boolean = false
    }
    override def TableClass = new TableClass {
      override def optionEnabled = false
    }
  }
}
