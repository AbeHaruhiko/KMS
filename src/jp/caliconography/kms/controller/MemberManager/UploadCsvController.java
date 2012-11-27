package jp.caliconography.kms.controller.MemberManager;

import jp.caliconography.kms.meta.ProcessResultMeta;
import jp.caliconography.kms.model.ProcessResult;
import jp.caliconography.kms.model.ProcessResult.ProcessStatus;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.MultipartRequestHandler;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.controller.upload.FileUploadException;

public class UploadCsvController extends Controller {

	private MemberManagerService service = new MemberManagerService();

	@Override
	public Navigation run() throws Exception {

		try {
			MultipartRequestHandler requestHandler = new MultipartRequestHandler(request);
			requestHandler.handle();
			FileItem fileItem = requestScope("uploadedFile");
			ProcessResult result = service.importCsv(fileItem);
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(ProcessResultMeta.get().modelToJson(result));
		    response.flushBuffer();

		} catch (FileUploadException e) {
//			response.sendError(500);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(ProcessResultMeta.get().modelToJson(new ProcessResult(ProcessStatus.ERROR, "ファイルアップロードに失敗しました。", 0)));
		    response.flushBuffer();

		} catch (Exception e) {
//			response.sendError(500);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(ProcessResultMeta.get().modelToJson(new ProcessResult(ProcessStatus.ERROR, "エラーが発生しました。", 0)));
		    response.flushBuffer();
		}
		return null;
	}

}
