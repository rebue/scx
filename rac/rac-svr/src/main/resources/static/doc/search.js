let api = [];
api.push({
    alias: 'RacSysCtrl',
    order: '1',
    link: '系统控制器',
    desc: '系统控制器',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '添加系统',
});
api[0].list.push({
    order: '2',
    desc: '修改系统的信息',
});
api[0].list.push({
    order: '3',
    desc: '删除系统',
});
api[0].list.push({
    order: '4',
    desc: '获取单个系统的信息',
});
api[0].list.push({
    order: '5',
    desc: '判断系统是否存在',
});
api[0].list.push({
    order: '6',
    desc: '查询系统的信息',
});
api[0].list.push({
    order: '7',
    desc: '查询系统的信息',
});
api.push({
    alias: 'RacPermCtrl',
    order: '2',
    link: '权限控制器',
    desc: '权限控制器',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '添加权限',
});
api[1].list.push({
    order: '2',
    desc: '修改权限的信息',
});
api[1].list.push({
    order: '3',
    desc: '删除权限',
});
api[1].list.push({
    order: '4',
    desc: '获取单个权限的信息',
});
api[1].list.push({
    order: '5',
    desc: '判断权限是否存在',
});
api[1].list.push({
    order: '6',
    desc: '查询权限的信息',
});
api[1].list.push({
    order: '7',
    desc: '查询带分组的权限列表',
});
api[1].list.push({
    order: '8',
    desc: '上移动权限的信息',
});
api[1].list.push({
    order: '9',
    desc: '下移动权限的信息',
});
api[1].list.push({
    order: '10',
    desc: '启用权限',
});
api[1].list.push({
    order: '11',
    desc: '禁用权限',
});
api.push({
    alias: 'RacPermUrnCtrl',
    order: '3',
    link: '权限urn控制器',
    desc: '权限URN控制器',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '添加权限URN',
});
api[2].list.push({
    order: '2',
    desc: '添加修改URN',
});
api[2].list.push({
    order: '3',
    desc: '修改权限URN的信息',
});
api[2].list.push({
    order: '4',
    desc: '删除权限URN',
});
api[2].list.push({
    order: '5',
    desc: '获取单个权限URN的信息',
});
api[2].list.push({
    order: '6',
    desc: '判断权限URN是否存在',
});
api[2].list.push({
    order: '7',
    desc: '查询权限URN的信息',
});
api[2].list.push({
    order: '8',
    desc: '通过permId查询权限URN的信息',
});
api.push({
    alias: 'RacDicCtrl',
    order: '4',
    link: '字典控制器',
    desc: '字典控制器',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '添加字典',
});
api[3].list.push({
    order: '2',
    desc: '修改字典的信息',
});
api[3].list.push({
    order: '3',
    desc: '删除字典',
});
api[3].list.push({
    order: '4',
    desc: '获取单个字典的信息',
});
api[3].list.push({
    order: '5',
    desc: '判断字典是否存在',
});
api[3].list.push({
    order: '6',
    desc: '查询字典的信息',
});
api[3].list.push({
    order: '7',
    desc: '查询字典的信息',
});
api.push({
    alias: 'RacDomainCtrl',
    order: '5',
    link: '领域控制器',
    desc: '领域控制器',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '添加领域',
});
api[4].list.push({
    order: '2',
    desc: '修改领域的信息',
});
api[4].list.push({
    order: '3',
    desc: '删除领域',
});
api[4].list.push({
    order: '4',
    desc: '获取单个领域的信息',
});
api[4].list.push({
    order: '5',
    desc: '判断领域是否存在',
});
api[4].list.push({
    order: '6',
    desc: '查询领域的信息',
});
api[4].list.push({
    order: '7',
    desc: '查询领域的信息',
});
api.push({
    alias: 'RacPermMenuCtrl',
    order: '6',
    link: '权限菜单控制器',
    desc: '权限菜单控制器',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '添加权限菜单',
});
api[5].list.push({
    order: '2',
    desc: '添加/修改权限菜单',
});
api[5].list.push({
    order: '3',
    desc: '修改权限菜单的信息',
});
api[5].list.push({
    order: '4',
    desc: '删除权限菜单',
});
api[5].list.push({
    order: '5',
    desc: '获取单个权限菜单的信息',
});
api[5].list.push({
    order: '6',
    desc: '判断权限菜单是否存在',
});
api[5].list.push({
    order: '7',
    desc: '查询权限菜单的信息',
});
api[5].list.push({
    order: '8',
    desc: '查询权限菜单的信息',
});
api.push({
    alias: 'RacOrgCtrl',
    order: '7',
    link: '组织控制器',
    desc: '组织控制器',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '添加组织',
});
api[6].list.push({
    order: '2',
    desc: '添加组织和账户的关系',
});
api[6].list.push({
    order: '3',
    desc: '删除组织和账户的关系',
});
api[6].list.push({
    order: '4',
    desc: '修改组织的信息',
});
api[6].list.push({
    order: '5',
    desc: '更改组织与账户的关系',
});
api[6].list.push({
    order: '6',
    desc: '修改账户默认组织的信息',
});
api[6].list.push({
    order: '7',
    desc: '删除组织',
});
api[6].list.push({
    order: '8',
    desc: '获取单个组织的信息',
});
api[6].list.push({
    order: '9',
    desc: '判断组织是否存在',
});
api[6].list.push({
    order: '10',
    desc: '查询组织的信息',
});
api[6].list.push({
    order: '11',
    desc: '查询当前账户所在的组织的信息',
});
api[6].list.push({
    order: '12',
    desc: '查询可以添加的组织信息',
});
api[6].list.push({
    order: '13',
    desc: '查询组织的信息',
});
api.push({
    alias: 'RacPermGroupCtrl',
    order: '8',
    link: '权限分组控制器',
    desc: '权限分组控制器',
    list: []
})
api[7].list.push({
    order: '1',
    desc: '添加权限分组',
});
api[7].list.push({
    order: '2',
    desc: '修改权限分组的信息',
});
api[7].list.push({
    order: '3',
    desc: '删除权限分组',
});
api[7].list.push({
    order: '4',
    desc: '获取单个权限分组的信息',
});
api[7].list.push({
    order: '5',
    desc: '判断权限分组是否存在',
});
api[7].list.push({
    order: '6',
    desc: '查询权限分组的信息',
});
api[7].list.push({
    order: '7',
    desc: '上移动权限分组的信息',
});
api[7].list.push({
    order: '8',
    desc: '下移动权限分组的信息',
});
api[7].list.push({
    order: '9',
    desc: '启用权限分组',
});
api[7].list.push({
    order: '10',
    desc: '禁用权限分组',
});
api.push({
    alias: 'RacRoleCtrl',
    order: '9',
    link: '角色控制器',
    desc: '角色控制器',
    list: []
})
api[8].list.push({
    order: '1',
    desc: '添加角色',
});
api[8].list.push({
    order: '2',
    desc: '添加/修改角色和权限的关系',
});
api[8].list.push({
    order: '3',
    desc: '添加角色和账户的关系',
});
api[8].list.push({
    order: '4',
    desc: '删除角色和账户的关系',
});
api[8].list.push({
    order: '5',
    desc: '修改角色的信息',
});
api[8].list.push({
    order: '6',
    desc: '删除角色',
});
api[8].list.push({
    order: '7',
    desc: '获取单个角色的信息',
});
api[8].list.push({
    order: '8',
    desc: '判断角色是否存在',
});
api[8].list.push({
    order: '9',
    desc: '查询角色的信息',
});
api[8].list.push({
    order: '10',
    desc: '上移动角色的信息',
});
api[8].list.push({
    order: '11',
    desc: '下移动角色的信息',
});
api[8].list.push({
    order: '12',
    desc: '启用角色',
});
api[8].list.push({
    order: '13',
    desc: '禁用角色',
});
api[8].list.push({
    order: '14',
    desc: '查询角色已有的权限的关系',
});
api[8].list.push({
    order: '15',
    desc: '查询角色的信息',
});
api[8].list.push({
    order: '16',
    desc: '查询角色的信息',
});
api.push({
    alias: 'RacDicItemCtrl',
    order: '10',
    link: '字典项控制器',
    desc: '字典项控制器',
    list: []
})
api[9].list.push({
    order: '1',
    desc: '添加字典项',
});
api[9].list.push({
    order: '2',
    desc: '修改字典项的信息',
});
api[9].list.push({
    order: '3',
    desc: '删除字典项',
});
api[9].list.push({
    order: '4',
    desc: '获取单个字典项的信息',
});
api[9].list.push({
    order: '5',
    desc: '判断字典项是否存在',
});
api[9].list.push({
    order: '6',
    desc: '查询字典项的信息',
});
api.push({
    alias: 'RacOpLogCtrl',
    order: '11',
    link: '查询操作日志的信息',
    desc: '查询操作日志的信息',
    list: []
})
api[10].list.push({
    order: '1',
    desc: '添加操作日志',
});
api[10].list.push({
    order: '2',
    desc: '修改操作日志的信息',
});
api[10].list.push({
    order: '3',
    desc: '删除操作日志',
});
api[10].list.push({
    order: '4',
    desc: '获取单个操作日志的信息',
});
api[10].list.push({
    order: '5',
    desc: '判断操作日志是否存在',
});
api[10].list.push({
    order: '6',
    desc: '查询操作日志的信息',
});
api.push({
    alias: 'RacLockLogCtrl',
    order: '12',
    link: '锁定日志控制器',
    desc: '锁定日志控制器',
    list: []
})
api[11].list.push({
    order: '1',
    desc: '删除锁定日志',
});
api[11].list.push({
    order: '2',
    desc: '获取单个锁定日志的信息',
});
api[11].list.push({
    order: '3',
    desc: '判断锁定日志是否存在',
});
api[11].list.push({
    order: '4',
    desc: '查询锁定日志的信息',
});
api[11].list.push({
    order: '5',
    desc: '添加锁定日志',
});
api[11].list.push({
    order: '6',
    desc: '修改锁定日志的信息',
});
api.push({
    alias: 'RacSignUpCtrl',
    order: '13',
    link: 'api账户注册的控制器',
    desc: 'API账户注册的控制器',
    list: []
})
api[12].list.push({
    order: '1',
    desc: '通过账户名称注册',
});
api.push({
    alias: 'RacAgentSignOutCtrl',
    order: '14',
    link: '退出代理登录的控制器',
    desc: '退出代理登录的控制器',
    list: []
})
api[13].list.push({
    order: '1',
    desc: '退出代理登录',
});
api.push({
    alias: 'RacAgentSignInCtrl',
    order: '15',
    link: '代理登录的控制器',
    desc: '代理登录的控制器',
    list: []
})
api[14].list.push({
    order: '1',
    desc: '代理登录',
});
api.push({
    alias: 'RacSignInCtrl',
    order: '16',
    link: '账户登录的控制器',
    desc: '账户登录的控制器',
    list: []
})
api[15].list.push({
    order: '1',
    desc: '通过账户名称登录',
});
api.push({
    alias: 'RacAccountCtrl',
    order: '17',
    link: '账户控制器',
    desc: '账户控制器',
    list: []
})
api[16].list.push({
    order: '1',
    desc: '添加账户',
});
api[16].list.push({
    order: '2',
    desc: '修改账户的信息',
});
api[16].list.push({
    order: '3',
    desc: '删除账户',
});
api[16].list.push({
    order: '4',
    desc: '获取单个账户的信息',
});
api[16].list.push({
    order: '5',
    desc: '判断账户是否存在',
});
api[16].list.push({
    order: '6',
    desc: '查询账户的信息',
});
api[16].list.push({
    order: '7',
    desc: '查询账户的信息',
});
api[16].list.push({
    order: '8',
    desc: '修改账户登录密码',
});
api[16].list.push({
    order: '9',
    desc: '启用账户',
});
api[16].list.push({
    order: '10',
    desc: '禁用账户',
});
api[16].list.push({
    order: '11',
    desc: '上传头像',
});
api[16].list.push({
    order: '12',
    desc: '获取当前账户信息',
});
api.push({
    alias: 'RacUserCtrl',
    order: '18',
    link: '用户控制器',
    desc: '用户控制器',
    list: []
})
api[17].list.push({
    order: '1',
    desc: '添加用户',
});
api[17].list.push({
    order: '2',
    desc: '修改用户的信息',
});
api[17].list.push({
    order: '3',
    desc: '删除用户',
});
api[17].list.push({
    order: '4',
    desc: '获取单个用户的信息',
});
api[17].list.push({
    order: '5',
    desc: '判断用户是否存在',
});
api[17].list.push({
    order: '6',
    desc: '查询用户的信息',
});
api.push({
    alias: 'dict',
    order: '19',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    let doc;
    if (apiData.length > 0) {
        for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="#_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}