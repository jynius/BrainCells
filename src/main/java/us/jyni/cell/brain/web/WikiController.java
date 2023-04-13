/**
 * 
 */
package us.jyni.cell.brain.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.entity.NodeLinks;
import us.jyni.cell.brain.entity.WikiCategory;
import us.jyni.cell.brain.entity.WikiLink;
import us.jyni.cell.brain.entity.WikiLinkView;
import us.jyni.cell.brain.entity.WikiPage;
import us.jyni.cell.brain.entity.WikiPageView;
import us.jyni.cell.brain.service.WikiCategoryService;
import us.jyni.cell.brain.service.WikiLinkService;
import us.jyni.cell.brain.service.WikiPageService;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Controller
@RequestMapping("/wiki")
public class WikiController {

	private static final Logger LOG = LoggerFactory.getLogger(WikiController.class);
	
	private static final File DATA_FOLDER = new File("C:\\Repositories\\git\\202303_JpaStudy\\BrainCells\\newBrainCells\\dataset\\wiki");
	private static final int PARTITION_SIZE = 10000;

	@Resource
	private WikiCategoryService categoryService;
	@Resource
	private WikiPageService pageService;
	@Resource
	private WikiLinkService linkService;

	/**
	 * <p>Neuron의 전체 목록에 대해 diagram을 그림.</p>
	 * 
	 * @param model front에 표시할 데이타를 담을 container
	 * @return view path
	 */
	@GetMapping
	public String diagram(Model model) {
		return "wiki/diagram";
	}

	/**
	 * <p>Neuron의 전체 목록</p>
	 * 
	 * @param model front에 표시할 데이타를 담을 container
	 * @return view path
	 */
	@GetMapping("/nodelinks")
	@ResponseBody
	public NodeLinks pages(WikiPageFilter filter) {
		
		Page<WikiPageView> pages = pageService.findPage(filter, WikiPageView.class);
		WikiLinkFilter linkFilter = new WikiLinkFilter();
		linkFilter.setPageId(filter.getPageId());
		linkFilter.setPages(pages);
		List<WikiLinkView> links = linkService.findAll(linkFilter, WikiLinkView.class);

		return new NodeLinks(pages.getContent(), links);
	}
	
	@GetMapping("/load0")
	@ResponseBody
	public String load0() {
		
		readData("wiki-topcats-page-names.txt", e->{
			int p = e.indexOf(' ');
			WikiPage page = new WikiPage();
			page.setId(Long.getLong(e.substring(0, p)));
			page.setName(e.substring(p+1).trim());
			pageService.save(page);
		});
		
		readData("wiki-topcats-categories.txt", e->{
			int p = e.indexOf(';');
			WikiCategory category = new WikiCategory();
			category.setName(e.substring(9, p));
			category.setPages(
				Arrays.stream(e.substring(p+1).split(" ")).map(i->{
					WikiPage page = new WikiPage();
					page.setId(Long.valueOf(i));
					return page;
				}).toList()
			);
			categoryService.save(category);
		});
		
		readData("wiki-topcats.txt", e->{
			int p = e.indexOf(' ');
			WikiLink link = new WikiLink();
			link.setPrev(Long.valueOf(e.substring(0, p)));
			link.setNext(Long.valueOf(e.substring(p + 1)));
			linkService.save(link);
		});
		
		return "complete!";
	}

	@GetMapping("/load1")
	@ResponseBody
	public String load1() {
		
		readBulkData("wiki-topcats-page-names.txt", l->pageService.saveAll(
				l.stream()
				.map(e->{
					int p = e.indexOf(' ');
					WikiPage page = new WikiPage();
					page.setId(Long.getLong(e.substring(0, p)));
					page.setName(e.substring(p+1).trim());
					return page;
				})
				.collect(Collectors.toList())
		));
		
//		readBulkData("wiki-topcats-categories.txt", l->categoryService.saveAll(
//				l.stream()
//				.map(e->{
//					int p = e.indexOf(';');
//					WikiCategory category = new WikiCategory();
//					category.setName(e.substring(9, p));
//					category.setPages(
//						Arrays.stream(e.substring(p+1).split(" "))
//						.filter(s->(s!=null && !s.trim().isEmpty()))
//						.map(s->{
//							WikiPage page = new WikiPage();
//							page.setId(Long.valueOf(s));
//							return page;
//						}).toList()
//					);
//					return category;
//				})
//				.collect(Collectors.toList())
//		));
		
//		readBulkData("wiki-topcats.txt", l->linkService.saveAll(
//				l.stream()
//				.map(e->{
//					int p = e.indexOf(' ');
//					WikiLink link = new WikiLink();
//					link.setPrev(Long.valueOf(e.substring(0, p)));
//					link.setNext(Long.valueOf(e.substring(p + 1)));
//					return link;
//				})
//				.collect(Collectors.toList())
//		));
		
		return "complete!";
	}

	private Integer i;
	private void readBulkData(String file,  Consumer<List<String>> f) {

		LOG.info("read bulk data from the file [{}]", file);
		i = 0;
		
		try (
			FileReader r = new FileReader(new File(DATA_FOLDER, file));
			BufferedReader br = new BufferedReader(r);
		) {
			br.lines()
//			.filter(e->i++<10000)
			.collect(Collectors.groupingBy(e->i++/PARTITION_SIZE))
			.values().stream()
			.forEach(f);
			
			LOG.info("finished reading data from the file [{}]", file);
		}
		catch (FileNotFoundException e) {
			LOG.error("can't find file [{}]", file, e);
		}
		catch (IOException e) {
			LOG.error("can't read file [{}]", file, e);
		}
	}

	private void readData(String file, Consumer<String> f) {

		LOG.info("read data from the file [{}]", file);
		
		try (
			FileReader r = new FileReader(new File(DATA_FOLDER, file));
			BufferedReader br = new BufferedReader(r);
		) {
			br.lines().forEach(f);
			LOG.info("finished reading data from the file [{}]", file);
		}
		catch (FileNotFoundException e) {
			LOG.error("can't find file [{}]", file, e);
		}
		catch (IOException e) {
			LOG.error("can't read file [{}]", file, e);
		}
	}
}
