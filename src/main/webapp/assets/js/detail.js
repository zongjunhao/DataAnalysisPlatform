var mydata = [{
    name: '节点1',
    nodeId: 0,
}, {
    name: '节点2',
    nodeId: 1,
}, {
    name: '节点3',
    nodeId: 2,
}, {
    name: '节点4',
    nodeId: 3,
}];
var nodes = [];
var flag = false;
// 基于准备好的dom，初始化echarts实例
let myChart = echarts.init(document.getElementById('chart'));

// 指定图表的配置项和数据
let option = {
    title: {
        text: ''
    },
    tooltip: {},
    animationDurationUpdate: 1500,
    animationEasingUpdate: 'quinticInOut',
    series: [
        {
            type: 'graph',
            layout: 'circular',
            symbolSize: 50,
            roam: true,
            label: {
                show: true
            },
            edgeSymbol: ['none', 'none'],
            edgeSymbolSize: 10,
            edgeLabel: {
                fontSize: 20
            },
            data: mydata,
            // [{
            //     name: '节点1',
            //     nodeId: 0,
            // }, {
            //     name: '节点2',
            //     nodeId: 1,
            // }, {
            //     name: '节点3',
            //     nodeId: 2,
            // }, {
            //     name: '节点4',
            //     nodeId: 3,
            // }],
            links: [{
                source: 0,
                target: 1,
            }, {
                source: 0,
                target: 2,
            }, {
                source: 1,
                target: 2,
            }, {
                source: 1,
                target: 3,
            }, {
                source: 0,
                target: 3,
            }],
            lineStyle: {
                opacity: 0.9,
                width: 2,
            }
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

myChart.on('click', function (params) {
   if (params.componentType === 'series') {
        if (params.seriesType === 'graph') {
            if (params.dataType === 'edge') {
                // 点击到了 graph 的 edge（边）上。
                console.log('点击到了 graph 的 edge（边）上');
            } else {
                // 点击到了 graph 的 node（节点）上。
                console.log('点击到了 graph 的 node（节点）上');
                console.log(params);
                getNodeAttri(params.data.nodeId);
            }
        }
    }
});

function getNodeAttri(nodeId) {
    console.log('获取节点ID为' + nodeId + '的详细信息');
    if (flag) {
        nodes.push(nodeId);
    }
    if (nodes.length == 2) {
        flag = false;
        getSimilarity();
    }
}

function switchMode() {
    console.log('获取相似度');
    flag = true;
}

function getSimilarity() {
    console.log('调用python脚本获取相似度');
    console.log(nodes)
    // $.ajax({
    //     type: "POST",
    //     url: "getSimilarity",
    //     datatype: 'json',
    //     data: {
    //         "taskId": $.session.get('taskId'),
    //         "nodeId1": nodes[0],
    //         "nodeId2": nodes[1],
    //     }, // 发送数据
    //     error: function () {
    //         layer.msg('request failed', {
    //             time: 1000
    //         });
    //     },
    //     success: function (jsonobj) {
    //         if (jsonobj.resultCode === "4000") {//获取相似度成功
    //             console.log(jsonobj.data);
                    
    //         } else {
    //             layer.msg(jsonobj.resultDesc, {
    //                 time: 1000
    //             });
    //         }
    //     },
    // });
    nodes = [];
}