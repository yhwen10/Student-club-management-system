const menu = {
    list() {
        return [
            {
                backMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "学生",
                                menuJump: "列表",
                                tableName: "xuesheng"
                            }
                        ],
                        menu: "学生管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "社长",
                                menuJump: "列表",
                                tableName: "shezhang"
                            }
                        ],
                        menu: "社长管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除"],
                                menu: "社团分类",
                                menuJump: "列表",
                                tableName: "shetuanfenlei"
                            }
                        ],
                        menu: "社团分类管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除", "审核", "查看评论"],
                                menu: "社团信息",
                                menuJump: "列表",
                                tableName: "shetuanxinxi"
                            }
                        ],
                        menu: "社团信息管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "加入社团",
                                menuJump: "列表",
                                tableName: "jiarushetuan"
                            }
                        ],
                        menu: "加入社团管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "社团成员",
                                menuJump: "列表",
                                tableName: "shetuanchengyuan"
                            }
                        ],
                        menu: "社团成员管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除", "审核"],
                                menu: "社团活动",
                                menuJump: "列表",
                                tableName: "shetuanhuodong"
                            }
                        ],
                        menu: "社团活动管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "活动报名",
                                menuJump: "列表",
                                tableName: "huodongbaoming"
                            }
                        ],
                        menu: "活动报名管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除"],
                                menu: "社团新闻",
                                tableName: "news"
                            },
                            {
                                buttons: ["查看", "修改"],
                                menu: "轮播图管理",
                                tableName: "config"
                            }
                        ],
                        menu: "系统管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "导出", "统计"],
                                menu: "社团统计",
                                menuJump: "统计",
                                tableName: "shetuantongji"
                            }
                        ],
                        menu: "社团统计管理"
                    }
                ],
                frontMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "申请加入"],
                                menu: "社团信息列表",
                                menuJump: "列表",
                                tableName: "shetuanxinxi"
                            }
                        ],
                        menu: "社团信息模块"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "报名"],
                                menu: "社团活动列表",
                                menuJump: "列表",
                                tableName: "shetuanhuodong"
                            }
                        ],
                        menu: "社团活动模块"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "导出"],
                                menu: "社团统计列表",
                                menuJump: "统计",
                                tableName: "shetuantongji"
                            }
                        ],
                        menu: "社团统计模块"
                    }
                ],
                hasBackLogin: "是",
                hasBackRegister: "否",
                hasFrontLogin: "否",
                hasFrontRegister: "否",
                roleName: "管理员",
                tableName: "users"
            },
            {
                backMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "删除"],
                                menu: "加入社团",
                                menuJump: "列表",
                                tableName: "jiarushetuan"
                            }
                        ],
                        menu: "加入社团管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "删除"],
                                menu: "活动报名",
                                menuJump: "列表",
                                tableName: "huodongbaoming"
                            }
                        ],
                        menu: "活动报名管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "删除"],
                                menu: "我的收藏管理",
                                tableName: "storeup"
                            }
                        ],
                        menu: "我的收藏管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "分析", "导出"],
                                menu: "学生兴趣分析",
                                menuJump: "分析",
                                tableName: "studentInterest"
                            }
                        ],
                        menu: "学生兴趣分析管理"
                    }
                ],
                frontMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "申请加入"],
                                menu: "社团信息列表",
                                menuJump: "列表",
                                tableName: "shetuanxinxi"
                            }
                        ],
                        menu: "社团信息模块"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "报名"],
                                menu: "社团活动列表",
                                menuJump: "列表",
                                tableName: "shetuanhuodong"
                            }
                        ],
                        menu: "社团活动模块"
                    }
                ],
                hasBackLogin: "是",
                hasBackRegister: "否",
                hasFrontLogin: "是",
                hasFrontRegister: "是",
                roleName: "学生",
                tableName: "xuesheng"
            },
            {
                backMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "删除", "回复"],
                                menu: "评论管理",
                                menuJump: "列表",
                                tableName: "comments"
                            }
                        ],
                        menu: "评论管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除", "查看评论"],
                                menu: "社团信息",
                                menuJump: "列表",
                                tableName: "shetuanxinxi"
                            }
                        ],
                        menu: "社团信息管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "删除", "审核", "成员登记"],
                                menu: "加入社团",
                                menuJump: "列表",
                                tableName: "jiarushetuan"
                            }
                        ],
                        menu: "加入社团管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "修改", "删除"],
                                menu: "社团成员",
                                menuJump: "列表",
                                tableName: "shetuanchengyuan"
                            }
                        ],
                        menu: "社团成员管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["新增", "查看", "修改", "删除"],
                                menu: "社团活动",
                                menuJump: "列表",
                                tableName: "shetuanhuodong"
                            }
                        ],
                        menu: "社团活动管理"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "删除", "审核"],
                                menu: "活动报名",
                                menuJump: "列表",
                                tableName: "huodongbaoming"
                            }
                        ],
                        menu: "活动报名管理"
                    }
                ],
                frontMenu: [
                    {
                        child: [
                            {
                                buttons: ["查看", "申请加入"],
                                menu: "社团信息列表",
                                menuJump: "列表",
                                tableName: "shetuanxinxi"
                            }
                        ],
                        menu: "社团信息模块"
                    },
                    {
                        child: [
                            {
                                buttons: ["查看", "报名"],
                                menu: "社团活动列表",
                                menuJump: "列表",
                                tableName: "shetuanhuodong"
                            }
                        ],
                        menu: "社团活动模块"
                    }
                ],
                hasBackLogin: "是",
                hasBackRegister: "否",
                hasFrontLogin: "否",
                hasFrontRegister: "是",
                roleName: "社长",
                tableName: "shezhang"
            }
        ];
    }
};

export default menu;



