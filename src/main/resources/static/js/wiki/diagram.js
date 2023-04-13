/**
 * 
 */
$(function() {
	
    const panel = d3.select("#panel");
    const $panel = $('#panel');

    const params = {}
    $.get('/wiki/nodelinks', params, function(data) {
		load(data.nodes, data.links);
	});
    
	const load = function(nodes, links) {
	    
	    // 그래프 생성
	    const simulation = d3.forceSimulation(nodes)
	      .force("link",   d3.forceLink(links).id(function(d) { return d.nodeId; }))
	      .force("charge", d3.forceManyBody().strength(-10000))
	      .force("center", d3.forceCenter($panel.width()/2, $panel.height()/2));
	    
	    // 노드 생성
	    const node = panel.append("g")
	      .attr("stroke", "#fff")
	      .attr("stroke-width", 1.5)
	      .selectAll("circle")
	      .data(nodes)
	      .join("circle")
	      .attr("r", 50)
	      .attr("fill", "#69b3a2")
	      .call(drag(simulation));
	    
	    // 링크 생성
	    const link = panel.append("g")
	      .attr("stroke", "#999")
	      .attr("stroke-opacity", 0.6)
	      .selectAll("line")
	      .data(links)
	      .join("line")
	      .attr("stroke-width", function(d) { return Math.sqrt(d.strength); });
	    
	    // 노드 라벨 생성
	    const label = panel.append("g")
	      .attr("class", "labels")
	      .selectAll("text")
	      .data(nodes)
	      .enter()
	      .append("text")
	      .attr("class", "label")
	      .text(function(d) { return d.label; });
	    
	    // 시뮬레이션 시작
	    simulation.on("tick", ticked);
	    node.on("click", function(d) {
		    const params = {pageId: d.target.__data__.nodeId}
		    $.get('/wiki/nodelinks', params, function(data) {
				panel.selectAll("*").remove();
				load(data.nodes, data.links);
			});
		});
	    
	    // 드래그 함수 정의
	    function drag(simulation) {
	    
	      function dragstarted(event, d) {
	        if (!event.active) simulation.alphaTarget(0.3).restart();
	        d.fx = d.x;
	        d.fy = d.y;
	      }
	    
	      function dragged(event, d) {
	        d.fx = event.x;
	        d.fy = event.y;
	      }
	    
	      function dragended(event, d) {
	        if (!event.active) simulation.alphaTarget(0);
	        d.fx = null;
	        d.fy = null;
	      }
	    
	      return d3.drag()
	          .on("start", dragstarted)
	          .on("drag", dragged)
	          .on("end", dragended);
	    }
	    
	    // 틱 함수 정의
	    function ticked() {
	    
	        link
	            .attr("x1", function(d) {
	                return d.source.x;
	            })
	            .attr("y1", function(d) {
	                return d.source.y;
	            })
	            .attr("x2", function(d) {
	                return d.target.x;
	            })
	            .attr("y2", function(d) {
	                return d.target.y;
	            });
	    
	        node
	            .attr("cx", function(d) {
	                return d.x;
	            })
	            .attr("cy", function(d) {
	                return d.y;
	            });
	    
	        label
	            .attr("x", function(d) {
	                return d.x + 10;
	            })
	            .attr("y", function(d) {
	                return d.y - 10;
	            });
	    }
	}
});
