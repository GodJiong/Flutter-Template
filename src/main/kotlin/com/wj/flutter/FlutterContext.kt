import com.intellij.codeInsight.template.TemplateActionContext
import com.intellij.codeInsight.template.TemplateContextType

/**
 * @author wj
 * @date 2023/4/3
 * @description：FlutterContext
 */
class FlutterContext private constructor() : TemplateContextType("FLUTTER", "Flutter") {
    override fun isInContext(templateActionContext: TemplateActionContext): Boolean {
        return templateActionContext.file.name.endsWith(".dart")
    }
}
