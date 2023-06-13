package com.kinetix.surveyengine.restful;

public enum RetCode {


    /**
     * 用户模块错误信息
     * 10001 开始 至 19999
     */
    US_LOGIN_USER_OR_PASSWORD_ERROR(10001, "用户名或密码错误！"),
    US_NO_EXIT(10002, "用户不存在"),
    USER_TYPE_NOT_EXIT(10021, "用户类型存在"),
    USER_OLD_PASSWORD_NOT_RIGHT(10003, "旧密码不正确"),
    USER_IS_EXIT(10004, "用户信息已存在"),
    USER_CODE_IS_EXIT(10005, "用户编号已存在"),
    USER_PHONE_IS_EXIT(10006, "电话号码已存在"),
    USER_CARDNUM_IS_EXIT(10007, "身份证号码已存在"),
    FILENAME_FOMART_ISNOT(10008, "文件格式不正确"),
    FILE_IS_NULL(10009, "文件内容为空，无数据"),
    NOTICE_USER_LIST_NULL(10010, "公告接收人员为空"),
    CLIENT_IS_NOT_EXIT(10011, "企业/学校信息不存在"),
    USER_EAMIL_EXIT(10012, "邮箱已存在"),
    FILE_CREATE_FAIL(10013, "文件生成失败"),
    NOT_ROLE_FAIL(10014, "没有登录权限"),
    NOT_USER_OR_UNUSE(10015, "用户不存在或不是正常状态"),
    USER_EFFECTIVE_FAIL(10016, "获取用户有效期失败 "),
    USER_IN_GROUP(10017, "有用户在用户组中，不允许删除 "),
    USER_ACCOUNT_HAS_MONEY(10018, "用户账户还有余额，不能删除"),
    USER_IDCARD_IS_NOT_NULL(10017, "证件号码不能为空 "),
    USER_IDCARD_DY_SIX(10019, "证件号码不能小于6位"),
    USER_CLIENT_INFO_FAIL(10020, "获取机构失败"),

    LOGIN_VERIFY_CODE_FAILURE(10100, "验证码已失效，请重新输入"),
    LOGIN_VERIFY_CODE_ERROR(10101, "验证码错误"),
    LOGIN_VERIFY_CODE_OVER_TIME(10102, "验证码已过期，重新获取"),
    LOGIN_VERIFY_CODE_ID_NULL(10103, "输入的验证码为空，请重新输入"),
    LOGIN_VERIFY_CODE_UUID_NULL(10104, "验证码不匹配"),

    USER_GROUP_NAME_NOT_NULL(10200, "用户组名称为空"),
    USER_GROUP_EXIST(10201, "用户组已存在，请勿重复添加"),
    USER_GROUP_NOT_EXIST(10202, "用户组不存在"),

    US_DEPT_EXIST(11000, "部门已存在，请勿重复添加"),
    US_DEPT_NOT_EXIST(11011, "部门不存在"),
    US_STATE_ERROR(11006, "用户状态异常"),
    US_ROLE_EXIST(11001, "角色已存在，请勿重复添加"),
    US_LIST_FIELD_VIOLATIONS(11002, "显示字段不得少于5项，最多支持10个筛选字段"),
    US_P_DEPT_EQUALS(11003, "选择上级不能为自身层级"),
    US_UPDATE_DEPT_IS_NEXT(12005, "不能移至所属下级部门"),

    US_BITCH_ADD_ERROR(11003, "批量添加用户信息失败"),
    US_GET_USERINFO_ERROR(11004, "获取用户信息失败"),
    US_GET_USER_DEPT_INFO_ERROR(11005, "获取用户部门信息失败"),
    US_GET_USER_TYPE_INFO_ERROR(11006, "获取用户类型信息失败"),
    US_GET_USER_ROLE_INFO_ERROR(11007, "获取用户职位信息失败"),
    US_GET_USER_DEPT_IDS_ERROR(11008, "获取用户部门Id失败"),
    US_DEPT_NOTCAN_DEL(11009, "下存在用户，无法删除"),
    US_DEPT_USER_IS_NOT_NULL(11010, "请选择用户"),
    US_HAS_CHILDDEPT_NOTCAN_DEL(11011, "下存在下级部门，无法删除"),
    US_CARD_STATUS_FALSE(11012, "卡状态不对"),
    US_CARD_NO_EXIST(11013, "卡id不存在"),
    US_ACCOUNT_NOT_EXIST(11014, "用户账户不存在"),
    US_ACCOUNT_HAS_TWO(11015, "用户存在多个账户"),
    US_STATE_ISNOT_NOERMAL(11016, "用户已删除"),
    US_ORG_ISNULL(11017, "机构不能为空"),
    USER_NAME_IS_EXIT(11018, "用户名已存在"),
    USER_CARD_PHYSICS_IS_EXIT(11019, "卡物理号已存在"),
    USER_GROUP_IFO_BYID(11020, "根据组id获取用户组异常"),
    USER_ROLE_IFO_BYID(11021, "根据职位id获取用户职位异常"),
    USER_TYPE_IFO_BYID(11022, "根据类型id获取用户类型异常"),

    USER_INFO_BYTYPEID(11023, "根据类型id获取用户异常"),
    USER_INFO_BYDEPTID(11024, "根据部门id获取用户异常"),
    USER_INFO_BYROLEID(11025, "根据职位id获取用户异常"),
    USER_INFO_BYGROUPID(11026, "根据组id获取用户异常"),
    USER_CARD_LOSS_EXIT(11024, "卡已挂失"),
    USER_CARD_DEL_EXIT(11025, "卡已删除"),
    USER_NUMBERS_EXCEEDED_MAX_LIMIT(11026, "正常用户数量超过机构最大限制人数"),

    US_TYPE_EXIST(12001, "用户类型已存在，请勿重复添加"),
    US_TYPE_NOT_DEL(12002, "用户类型下存在用户，不能删除"),
    DEPT_INFO_BYID(12003, "根据id获取部门信息异常"),
    DEPT_INFO_BYCLIENT(12004, "根据企业获取部门信息异常"),
    US_INFO_BYIFD(12005, "根据id获取用户信息异常"),
    US_CARD_INFO_BYID(12006, "根据卡id获取卡信息异常"),
    US_CARD_NOT_EXIST(12007, "此卡用户不存在"),
    US_DOOR_RULE_NOT_EXIST(12008, "此用户已被门禁规则使用"),
    US_ATT_RULE_NOT_EXIST(12009, "此用户已被考勤规则使用"),
    US_SG_RULE_NOT_EXIST(12009, "此用户已被宿管规则使用"),
    US_CARD_BINDING_EXIST(12010, "卡号已绑定"),

    US_QR_NOT_EXIST(12021, "二维码未使用或已过期"),
    US_CLIENT_NAME_EXISTED(12022, "机构名称已存在"),
    /**
     * 门禁模块错误信息
     * 14000 开始 至 14999
     */
    DO_DOOR_GROUP_IS_EXIT(14000, "门禁组已存在"),
    DO_IN_DOOR_EQUIPMENT_IS_EXIT(14001, "进门设备已经被其他门禁使用"),
    DO_OUT_DOOR_EQUIPMENT_IS_EXIT(14002, "出门设备已经被其他门禁使用"),
    DO_IN_DOOR_NOT_EQUAL(14003, "多个进门端口不能绑定同一个门禁"),
    DO_OUT_DOOR_NOT_EQUAL(14004, "多个出门端口不能绑定同一个门禁"),
    DO_TYPE_is_NOT_EQUAL(14005, "门禁类型不能为空"),
    DO_DOOR_NOT_EQUAL_CONTROLLER(14006, "该门禁设备未绑定控制器"),
    DO_DOOR_BINDING_EXCEED(14007, "该门已超过最大绑定数"),
    DO_WX_CLIENTID_EXCEED(14008, "机构未绑定公众号"),
    DO_WX_LOG_PASSWORD_OR_PHONE_EXCEED(14009, "编号或者密码错误"),
    DO_WX_LEAVE_EXAMINE_EXCEED(14010, "审批人未绑定"),
    DO_WX_LEAVE_COPY_EXCEED(14011, "抄送人未绑定"),
    DO_WX_LEAVE_TRANSMIT_EXCEED(14012, "转发人未绑定"),
    DO_WX_REGISTER_USERNAME_PHONE_EXCEED(14013, "用户手机号或姓名错误"),
    DO_WX_LEAVE_CHANCE_EXCEED(14014, "审批人未绑定"),
    DO_WX_LEAVE_USER_BIND_EXCEED(14015, "该用户已绑定"),
    DO_WX_LEAVE_FLOW_EXCEED(14016, "审批流程不存在"),
    DO_WX_LEAVE_LOG_AUDIT_EXCEED(14017, "该申请已审批"),
    DO_LEAVELOG_STATE_EXAMINE_IN(14018, "请假记录未审批/审批中，不允许删除"),
    DO_LEAVEFLOW_LEVEL_NULL(14019, "不能隔级设置请假审批人"),
    DO_LEAVEFLOW_LEVEL_SAME(14020, "任意两级请假审批人不能相同"),
    DO_LEAVEFLOW_USRE_NOTBIND_WX(14021, "审批人未绑定微信"),
    DO_LEAVEFLOW_COPYUSRE_NOTBIND_WX(14022, "抄送人未绑定微信"),
    DO_RECORD_EXIST(14023, "记录已存在"),
    DO_WX_LEAVE_PATRIARCH_BIND_EXCEED(14024, "家长绑定数量已达上限"),
    DO_ACCESSLOG_UPLOAD_FAIL(14025, "插入门禁记录失败"),
    DOOR_INFO_BYID_FAIL(14026, "根据门id获取门信息失败"),
    DO_WX_REGISTER_PASSWOR_EXCEED(14027, "密码错误"),
    DO_WX_REGISTER_USERTYPE_EXCEED(14028, "用户类型错误"),
    DO_WX_REGISTER_OPENID_EXCEED(14029, "微信账号已绑定"),
    DO_WX_VISIT_USERNAME_PHONE_EXCEED(14030, "请输入正确的被访人信息"),
    DO_WX_BYVISIT_NOTBIND_EXCEED(14031, "拜访人未绑定"),
    DO_WX_LEAVE_REPEALTIME_EXCEED(14032, "请假已生效不能撤销"),
    DO_WX_VISIT_REPEALTIME_EXCEED(14033, "拜访已生效不能撤销"),
    DOOR_RE_EQUIP_FAIL(14034, "获取门与控制器关系异常"),
    DO_WX_VISITSET_NO_EXCEED(14035, "访客设置不存在"),
    DO_ATTRULE_HA_BIND(14036, "已绑定其他考勤规则，请勿重复绑定"),
    DO_OPEN_KEY_CLICK(14037, "远程开门/一键开闸/关闸/还原 失败"),
    DO_HAIQING_OPEN_SUPORT(14038, "海清设备暂时只支持远程开门"),
    DO_NOT_SUPORT_OPEN(14039, "改设备暂不支持远程开门"),
    DO_WX_NAME_STOP_EXCEED(14040, "微信账号已停用"),
    DO_NO_VISIT_RULE_EXCEED(14041, "拜访人没有访客规则"),
    DO_NO__EXCEED(14041, "拜访人没有访客规则"),
    DO_NO_PHYSICALNO_USER(14042, "该物理卡号对应的用户不存在"),
    DO_EQUIPMENT_NOT_EXIST(14043, "该设备不存在"),
    DO_NOT_BIND_ADMIN(14044, "班级未绑定管理员，请联系班主任后再申请"),
    DO_MODEL_INOUT_SAME(14044, "双控时，进出门控制器数量要相等"),
    DO_BIND_CONTRO_IN(14045, "该门已绑定此控制的进门读头，不允许再次绑定此控制器的进门读头"),
    DO_BIND_CONTRO_OUT(14046, "该门已绑定此控制的进门读头，不允许再次绑定此控制器的进门读头"),
    DO_EQUIP_NOT_SUPPORT_REMOTE_OPEN(14047, "存在设备不支持远程开门"),
    DO_USER_NO_AUTH_OPEN_THIS_DOOR(14048, "用户没有这个门的权限"),
    DO_DOOR_OR_EQUIP_NOT_EXIST(14049, "设备不存在"),
    DO_DOOR_OR_EQUIP_NOT_ONLINE(14050, "设备不在线"),
    DO_VISIT_USER_NOT_EXIT(14051, "没有该用户的访客信息"),
    DO_VISIT_IS_EMPTY(14052, "没有该访客记录"),
    DO_VISIT_IS_DEL(14053, "该访客记录已删除"),
    DO_LEAVE_TIME_OVERLAPPING(14054, "当前请假与已有请假,时间有重叠"),

    /**
     * 设备模块错误信息
     * 20001 开始 至 29999weix
     */
    EQ_GROUP_NAME_EXIST(20001, "设备分组名已存在"),
    EQ_GROUP_NO_EXIT(20002, "设备组不存在"),
    EQ_NO_OPEN(20011, "机具未开通"),
    EQ_NO_WRITE_OFF(20012, "机具已注销"),
    EQ_IS_OPEN(20013, "机具已开通"),
    EQ_EQUIP_NOT_LINK(20021, "设备未链接网络"),
    EQ_ATTEND_PARAM_ERR(20030, "推送考勤机具参数失败"),
    EQ_ATTEND_USER_ERR(20031, "推送考勤人员名单失败"),
    EQ_OPEN_ERR(20020, "推送机具开通异常"),
    EQ_DOOR_USER_ERR(20022, "推送门禁人员名单失败"),
    EQUIP_NAME_IS_EXIT(20023, "设备名已存在"),
    EQUIP_NO_IS_EXIT(20024, "设备号已存在"),
    EQUIP_NOT_IS_EXIT(20025, "设备不存在"),
    EQ_GROUP_NO_CAN_ZJ(20026, "上级分组不能为当前修改分组"),
    EQ_AREA_NO_EXIT(20027, "设备分布图不存在"),
    EQ_AREA_DEL_NOT_NULL(20028, "请选择需要删除的设备分布图"),
    EQ_AREA_BIND_OTHER(20029, "已经被其他区域绑定"),
    EQ_DEPT_NOTCAN_DEL(20030, "该设备组下存在设备，无法删除"),
    EQ_HAS_CHILDDEPT_NOTCAN_DEL(20031, "该设备组下存在下级设备组，无法删除"),
    DOOR_EQUIP_NAME_IS_EXIT(20032, "控制器设备名已存在"),
    DOOR_EQUIP_NO_IS_EXIT(20033, "控制器设备号已存在"),
    EQ_PASSWORD_ERR(20034, "初始化密码错误"),
    EQUIP_GETBY_GROUPDS(20035, "获取设备组下的设备失败"),
    EQUIP_MAC_IS_EXIT(20024, "设唯一标识已存在"),
    EQUIP_INFO_BYID(20025, "根据设备id获取设备信息失败"),
    EQUIP_INOUT_STATE(20026, "进出状态无法识别  非1-进 2-出"),
    EQUIP_TYPE_INFO_FAIL(20027, "获取设备类型信息失败"),
    EQUIP_GROUP_INFO_BYID(20028, "根据设备组id获取设备组信息失败"),
    EQUIP_ISSUE_LOG_INFO(20029, "获取设备下发记录异常"),
    EQUIP_TYPE_IS_NULL(20030, "设备类型为空"),
    EQUIP_SUBTYPE_IS_NULL(20031, "设备型号为空"),
    EQUIP_NAME_IS_NULL(20032, "设备名为空"),
    EQUIP_NO_IS_NULL(20033, "设备编号为空"),
    EQUIP_MAC_IS_NULL(20034, "设备MAC为空"),
    EQUIP_SUBTYPE_NOT_EXIT(20035, "设备型号不存在"),
    EQGROUP_DEPT_HASBIND_DEL(20036, "该设备组有绑定部门，无法删除，请先解除绑定"),
    EQ_UPISSUELOG_FAIL(20037, "设备权限下发记录修改失败"),
    EQ_OPERIDS_IS_NULL(20038, "请选择要操作的设备"),
    EQ_HAS_VERSION_IS_NULL(20039, "选中设备中存在版本号为空的设备，无法进行升级"),
    EQ_TYPE_OR_VERSION_NOT_EQUALS(20040, "请确认所选设备是同一型号、同一版本号"),
    EQ_UPDATE_TYPE_IS_N(20041, "只能取消未升级、升级中的记录"),
    EQ_GROUP_TREE_FAIL(20042, "获取设备组树形图失败"),
    EQ_NUM_COUNT_FAIL(20043, "获取设备数量统计失败"),
    EQUIP_TYPE_NOT_DOOR(20050, "所选设备类型不是门类型"),
    EQUIP_BIND_EXCEED(20051, "该门禁控制器已经绑定了2个门，无法继续绑定，请更换其他门禁控制器后再试"),
    EQ_BIND_LIMIT(20052, "普通门禁控制器不可同时绑定进或者出，请确认后重试"),
    EQ_BIND_CHANNEL_LIMIT(20053, "人脸平板只能绑定通道类型"),

    EQ_REGION_NAME_EXIST(20060, "区域分组名已存在"),
    EQ_REGION_NO_CAN_ZJ(20026, "上级区域分组不能为当前修改分组"),
    EQ_REGION_NOT_EXIST(20030, "该区域分组不存在"),
    EQ_REGION_NOTCAN_DEL(20031, "该区域分组下存在下级，无法删除"),


    /**
     * 消费模块错误信息 30001-39999
     */
    TR_CARD_NO_EXIT(30001, "卡片信息不存在"),
    TR_CARD_HAS_TWO(30002, "存在两个一样卡片"),
    TR_MSG_NULL(30003, "记录上传为空"),
    TR_MONEY_LOW(30004, "余额不足"),
    TR_NO_EQUIP(30005, "机具不存在"),
    TR_CARD_STATUS_ERROR(30006, "卡片状态不正常"),
    TR_SOME_SUCCESS(30007, "部分成功"),
    TR_HAS_SUPNAME(30008, "商户号已经存在"),
    TR_NO_SUPNAME(30008, "必填参数为空"),
    TR_TIME_USE(30009, "餐别被使用，无法删除"),
    TR_QCODE_NO_EXIT(30010, "二维码暂不支持"),
    TR_ORDERNO_EXIT(30011, "订单号已存在"),
    TR_ENCRYPT_CHECK_EXIT(30012, "密文校验失败"),
    TR_SECRET_KEY_EXIT(30013, "秘钥已失效"),
    TR_LIMIT_TRADE_NUM_EXIT(30014, "消费次数已超额"),
    TR_LIMIT_MONEY_NUM_EXIT(30015, "消费金额已超额"),
    TR_QRCODE_ERR_EXIT(30016, "无效的二维码"),
    TR_QRCODE_TIME_EXIT(30017, "二维码已失效"),
    TR_TABLE_INEXISTENCE_EXIT(30018, "表不存在"),
    TR_LIMIT_SALE_NUM_EXIT(30019, "优惠限次已超"),
    TR_SUBSIDY_ERROR_EXIT(30020, "该补贴已经撤销，请勿重复撤销"),
    TR_UPLOAD_PARAMETER(30021, "消费必填参数为空"),
    TR_CONSUME_RULE_LIMIT(30022, "消费规则限制"),
    TR_CHECK_CODE_EXIT(30023, "消费密文为空"),
    TR_LIMIT_CONSUME_RULE_EXIT(30024, "用餐已结束，请下次光临"),
    TR_SUBSIDY_EMPTY_EXIT(30025, "补贴已清空,不能撤销"),
    TR_ORDERNO_NOT_EXIT(30026, "订单号不存在"),
    TR_USER_BLACK(30027, "用户在黑名中，不允许消费"),
    TR_USER_SZRMB_NO_SIGN(30028, "数字人民币账户未签约"),
    TR_USER_SZRMB_TERMINATE(30029, "数字人民币账户已解约"),
    TR_USER_SZRMB_REQUEST_FAIL(30030, "数字人民币接口请求失败"),
    TR_PARAM_ERROR(30031, "参数错误"),
    TR_QRCODE_COMPLETE(30032, "当前二维码已被使用"),
    TR_CONSUME_RULE_NOEXIST(30033, "当前用户没有权限，不允许消费"),
    TR_CONSUME_USER_NOT_IN_TIME(30034, "当前用户不在有效期，不允许消费"),
    TR_NUM_PARAM_ERROR(30035, "充值金额必须大于0元"),
    TR_BAT_RECHARGE_ERROR(30036, "请勿重复提交，或待处理结束重新提交"),
    TR_BAT_RECHARGE_DIF_ERROR(30037, "正在处理其他批量充值文件，请等待处理结束再提交"),
    /**
     * 餐饮模块错误信息 40001-49999
     */
    FB_USER_NO_REPAST_ORDER(40001, "用户没有订餐"),
    FB_ERROR_FORMAT(40002, "只支持jpg、png、gif"),
    FB_ERROR_NUMBER_RANGE(40003, "分配数量之合需等于总菜品库存"),
    FB_SHOP_MESSAGE_IS_EXIT(40004, "店铺名已存在"),
    FB_MENU_TYPE_IS_EXIT(40005, "菜名已存在"),
    FB_SHOP_IS_NOT_EXIT(40006, "商铺不存在"),
    FB_NOW_FBTIME_NOT_EXIT(40007, "当前时间不是就餐时间"),
    FB_FOOD_NOT_EXIT(40008, "未识别到您的订餐信息"),
    FB_SHOP_EQUIP_IS_EXIT(40009, "设备已绑定其他店铺，请重新选择设备"),
    FB_MENUTIME_OVER_TIME(40010, "该餐段已过可预约时间，暂不能订餐"),
    FB_MENU_NOT_RESEVER(40011, "该时间不能提前订餐"),
    CANNOT_RESEVER_FBMENU(40012, "不能提前订餐"),
    CANTSUPPORT_CST_OTHER_PAYCHANNEL(40013, "改餐饮暂不支持康索特外的其他方式支付"),
    CARDLEFT_SHORT_OF_ORDER_FAIL(40014, "余额不足，提交失败"),
    ORDER_NOT_EXIT(40015, "订单不存在"),
    ORDER_SUBMIT_FAIL(40016, "订单提交失败"),
    FB_USER_ROLE_NOT_CAN_NULL(40017, "可就餐职位不能为空"),
    FB_WEEk_TIME_CAN_NULL(40018, "重复周期时间不能为空"),
    FB_RFEE_NULL(40019, "没有查询到可退款的商品"),
    ORDER_FAILURE(40020, "订单已失效"),
    MULTI_REFUND(40021, "不能重复申请退款"),
    MULTI_OPREATE(40022, "不能重复操作"),
    CARD_NO_EXIT(400023, "账户不存在"),
    MENU_IS_UNSHELVE(400024,"餐品已下架"),
    MENU_IS_OVERTIME(400025,"菜品已过期，请重新加入购物车下单"),
    FB_MENU_IS_EXIT(40026, "当前种类下存在菜品"),
    FB_SHOP_HAVE_MENU(40027,"当前店铺下存在菜品"),
    FB_SHOP_HAVA_MENU_TYPE(40028,"当前店铺下存在菜品种类"),
    FB_SHOP_HAVA_QEUIP(40029,"当前店铺有绑定的设备"),


    /**
     * 宿管模块错误信息 70001-79999
     */
    SY_PARENTID_NOT_NULL(70001, "请选择上级节点"),
    SY_TYPE_NOT_NULL(70002, "上级类型不能为空"),
    SY_DEL_TYPE_NOT_NULL(70003, "删除类型不能为空"),
    SY_EDI_TYPE_NOT_NULL(70004, "修改类型不能为空"),
    SY_EDI_ID_NOT_NULL(70005, "请选择修改节点"),
    SY_DEL_ID_NOT_NULL(70006, "请选择删除节点"),
    SY_ID_NOT_NULL(70007, "节点id不能为空"),
    SY_ROOM_NOT_EDI(70008, "已有床位，无法修改房间类型"),
    SY_ROOM_NOT_ADD(70009, "房间类型为宿舍时才能添加床位"),
    SY_HAS_USER_NOT_EDI(70010, "床位已经有人使用，不能修改床位状态"),

    /**
     * 接口模块错误信息 50001-59999
     */
    MANUFACTURE_IS_NOT_EXIT(50001, "该企业不存在"),
    MANUFACTURE_NO_PERMISSION(50002, "该企业没有该接口访问权限"),
    MANUFACTURE_PERMISSION_IS_STOP(50003, "该企业的该接口已暂停"),
    MANUFACTURE_PERMISSION_IS_DROP(50004, "该企业的该接口已删除"),
    OPERATE_MSG_NO_EXIT(50005, "暂无操作信息"),
    USER_FACE_NO_EXIT(50006, "用户人脸照片不存在"),
    IN_APPID_OR_SIGN_NOT_NULL(50007, "appId/sign不能为空"),
    IN_APPID_OR_SIGN_IS_Invalid(50008, "appId/sign无效"),


    /**
     * 系统模块错误信息 60001-69999
     */
    SY_ADD_OPERATE_LOG_ERR(60001, "系统操作日志添加失败"),
    SY_ERROR_FORMAT(60002, "只支持jpg、png、gif，大小不超过5M的文件"),
    SY_ADD_RESOUCE_ERR(60003, "添加系统资源失败"),
    SY_UPLOAD_FILE_ERR(60004, "上传文件添加日志失败"),
    SY_GET_UPLOAD_FILE_ERR(60005, "获取上传文件信息失败"),
    SY_UPDATE_UPLOAD_FILE_ERR(60006, "修改上传文件记录失败"),
    SY_UPLOAD_USER_FACE_ERR(60007, "批量上传人脸资源失败"),
    DEAL_USER_FACE_ERR(60008, "处理人脸压缩资源失败"),
    SY_UPLOAD_FILE_FAIL(60009, "上传文件失败"),
    WX_FACE_ERROR(60009, "腾讯人脸秘钥获取失败"),
    SY_DEL_RESOUCE_ERR(60010, "删除系统资源失败"),
    SY_UPLOAD_PICTURE_FAIL(60011, "保存图片文件失败"),
    SY_CREATE_QUEUE_FAIL(60012, "系统动态创建mq消息队列失败"),
    SY_DEL_QUEUE_FAIL(60013, "根据队列名删除队列失败"),


    /**
     * 宿管
     */
    SG_TOO_MANY_USERS(70001, "选择用户人数超出床位数"),
    SG_TOO_UN_MANY_USERS(70002, "选择未分配用户人数超出床位数"),
    SG_TOO_BEEN_MANY_USERS(70003, "选择已分配用户人数超出床位数"),
    SG_BEDS_IS_NULL(70004, "选择床位为空"),
    SG_USER_IS_NULL(70005, "选择用户为空"),
    SG_USER_IS_NOT_STAY(70006, "该用户非住宿状态无法退宿"),
    SG_BEDS_IS_NOT_STAY(70006, "该床位为非占用状态无法退宿"),
    SG_UNABLE_TO_CHARGE(70007, "无法给该宿舍充电，请检查本宿舍名称是否与电控系统中相同"),
    SG_BEDS_ERROR(70008, "床位详情信息查询异常"),
    SG_LEAVE_DEPT_REUSE_ERROR(70009, "离寝部门已设置"),
    SG_LEAVE_STARTTIME_REUSE_ERROR(70010, "开始时间不能小于当前时间"),
    SG_NO_ROOM_OR_BEDS(70011, "你还没有寝室及床位，请申请后再试"),
    SG_DUPLICATED_APPLY(70012, "请等待老师审批通过后，再次申请"),
    SG_USER_SEX_EMPTY(70013, "该用户的性别为空，请选择好性别之后再来申请宿舍"),
    SG_USER_CLASS_EMPTY(70013, "该用户的班级为空，请选择好班级之后再来申请宿舍"),
    SG_SEX_TYPE_NOT_MATCH(70014, "要分配的学生性别和当前房间性别属性不匹配"),
    SG_USER_ROOM_SEX_TYPE_NOT_MATCH(70015, "请确认该房间所住的学生性别和该房间的性别属性是否一致"),

    /**
     * 停车场模块
     */
    CP_CAR_NUMBER_EXIT(80001, "车牌号已存在"),
    CP_CAR_YUYUE_EXIT(80002, "该车牌预约时间冲突，请重新选择时间"),


    /**
     * 考务模块 11001-11999
     */
    EX_EXAMSUBJECT_EXIT(11001, "科目已存在"),
    EX_EXAMROOM_NO_EXIT(11002, "考场编号已存在"),
    EX_EXAMSEAT_NOT_ZERO(11003, "座位行列不能为0"),
    EX_EXAM_NOW_DELROOM(11004, "有考试未结束，考场不能删除"),
    EX_EXAM_NOW_DELSEAT(11005, "座位正在被占用，不能删除"),
    EX_EXAM_NOW_DELSUBJECT(11006, "有考试未结束，科目不能删除"),
    EX_EXAM_RULE_NOT_EXIT(11007, "用户考试规则不存在"),
    EX_EXAM_NOT_EXIT(11008, "考试信息不存在"),
    EX_EXAM_INSERT_USER_FAIL(11009, "批量导入考生信息失败 -- 插入考试记录异常"),
    EX_EXAM_SUBJECT_IS_NULL(11010, "选择科目为空"),
    EX_EXAM_SUBJECT_IS_NOT_EXIST(11011, "科目信息不存在"),
    EX_EXAM_TIME_ERROR(11012, "考试信息时间设置有误"),
    EX_EXAM_ROOM_USED(11013, "考场已占用"),
    EX_EXAM_ROOM_IS_NOT_EXIST(11014, "考场信息不存在"),
    EX_EXAM_USER_IS_NULL(11015, "选择用户为空"),
    EX_EXAM_STARTED_NOT_ALLOWED_MODIFY(11016, "考试已开始，不允许修改"),
    EX_EXAM_DELETED_NOT_ALLOWED_MODIFY(11017, "考试已删除，不允许修改"),
    EX_EXAM_IS_NOT_EXIST(11018, "考试信息不存在"),
    EX_EXAM_DOOR_BIND_ROOM(11019, "门已经绑定考场。请勿重复绑定"),
    EX_EXAM_DOOR_BIND_DEL(11020, "删除考场绑定设备失败"),
    EX_EXAM_DEAL_PCRECORD(11021, "考试人证核验处理异常"),
    EX_EXAM_PUSH_ROOM_FAIL(11022, "推送考场信息异常"),
    EX_EXAM_PUSH_SEAT_FAIL(11023, "推送考场座位信息异常"),
    EX_EXAM_BIND_OUT_ALREDAY(11024, "门已绑定其他考场的出门设备"),
    EX_EXAM_BIND_IN_ALREDAY(11025, "门已绑定其他考场的进门设备"),
    EX_EXAM_LOG_QUERY(11026, "获取考试信息异常"),
    EX_EXAM_TEACHER_IS_LOG(11027, "考务人员有未开始/正在进行的监考，不能删除"),
    EX_EXAM_LOG_UPDATE(11028, "修改考试记录失败"),
    EX_EXAM_NOW_DELLOG(11029, "考试正在进行，不能删除"),
    EX_EXAM_BIND_EQUIP(11030, "设备已绑定考场，不能删除"),
    EX_EXAM_SUBTYPE_EXIT(11031, "科目类型已存在"),
    EX_EXAM_NOW_DELSUBTYPE(11032, "科目类型下有考试未结束，不能删除"),
    EX_EXAM_LOG_NOT_I(11033, "考生没有未开始的考试，不能进场"),
    EX_EXAM_INROOM_NOT(11034, "当前时间不能进场"),
    EX_EXAM_SEAT_NONULL(11035, "暂时没有空座位 不能进场"),

    EX_PRIMARY_EXAM_IS_NOT_EXIST(11036, "关联的考试信息不存在"),
    EX_MAKEUP_EXAM_IS_NOT_EXIST(11037, "已经有补考关联此初考信息"),
    EX_ORGAN_IS_EXIST(11038, "培训机构已存在"),
    EX_EXAM_NOW_DELORGAN(11039, "培训机构下有考试未结束，不能删除"),
    EX_EXAM_BIND_DELORGAN(11040, "培训机构下有考试绑定，不能删除"),

    /**
     * 移动模块 12001-12999
     */
    YD_PHOTO_IS_NOT_NULL(12001, "请选择需要删除的图片"),
    YD_P_TYPE_IS_NOT_NULL(12002, "请求参数异常：操作类型operateType不能为空"),
    YD_NOTICE_ID_IS_NOT_NULL(12003, "请选择需要操作的公告"),
    YD_USER_IS_NOT_BOUND(12004, "用户未绑定"),


    /**
     * 迎新模块 13001-13999
     */
    YX_FAQ_TYPE_WRONG(13001, "问题类型不存在"),
    YX_DEPT_ALREADY_BINDED(13002, "该部门已绑定了流程"),
    YX_USER_INFO_FALSE(13003, "请检查身份证号或姓名信息是否有误！"),
    YX_WELCOME_DATE_EXPIRED(13004, "迎新流程时间已经结束了！"),
    YX_WELCOME_DATE_NOT_BEGIN(13004, "迎新流程时间还未开始！"),
    YX_EVENT_EMPTY(13005, "该流程下无具体事件，请添加后重试！"),
    YX_NUM_FALSE(13006, "助学贷款减免或者贫困生减免金额不应大于总金额，请重新设置"),
    YX_EMPTY_ORIGINAL_SCORE(13007, "你还没有初始学分，无法申请！请联系老师导入初始学分"),
    YX_DUPLICATE_APPROVE_RULE(13008, "审批规则不能重复设置！"),
    YX_TOO_MANY_APPLY_RECORDS(13009, "请等待老师审批通过之后，再来申请"),
    YX_DUPLICATED_PHONE(13010, "该手机号已经被使用，请重新填写！"),
    YX_DEPT_NO_ADMIN(13011, "该部门暂无管理员，请设置好之后再重试!"),
    YX_CLASS_NO_ADMIN(13012, "你所处的班级暂无管理员，请联系老师设置好之后再重试!"),
    YX_BUILDING_NO_ADMIN(13013, "你所处的楼栋暂无管理员，请联系老师设置好之后再重试!"),
    YX_PROFESSIONAL_NO_ADMIN(13014, "你所处的专业暂无管理员，请联系老师设置好之后再重试!"),
    YX_FACULTY_NO_ADMIN(13015, "你所处的学院暂无管理员，请联系老师设置好之后再重试!"),
    YX_RECORDS_FACULTY_NO_ADMIN(13016, "之前的未审批记录中,有学生所在的学院暂无管理员,请审批后再进行操作或者选择其他选项！"),
    YX_RECORDS_PROFESSIONAL_NO_ADMIN(13017, "之前的未审批记录中,有学生所在的专业暂无管理员,请审批后再进行操作或者选择其他选项！"),
    YX_RECORDS_CLASS_NO_ADMIN(13018, "之前的未审批记录中,有学生所在的班级暂无管理员,请审批后再进行操作或者选择其他选项！"),
    YX_RECORDS_BUILDING_NO_ADMIN(13019, "之前的未审批记录中,有学生所在的楼栋暂无管理员,请审批后再进行操作或者选择其他选项！"),
    YX_TEACHER_NOT_DEAL(13020, "请等待老师处理完违纪之后,再做解除违纪的申请"),
    YX_NO_PERSON_DETECTED(13021, "没有查询到此身份证对应的人员，请重试"),
    YX_TOO_MANY_PERSON_DETECTED(13022, "该身份证号对应的人员不唯一，请检查后重试！"),
    YX_NO_PROCESS_DETECTED(13023, "你还没有任何的迎新流程(或流程中不存在报到事件或流程不在进行中)，请联系老师"),
    YX_DUPLICATE_ENSURE(13024, "请不要重复进行确认报到/缴费/物品领用等操作"),
    YX_FILTER_CONDITIONS_NULL(13025, "学生姓名/学号/身份证号不能全部为空"),
    YX_DUPLICATE_AUTHENTICATE(13026, "你已在平台/访客机上报到过，无需再次验证"),

    /**
     * 综合模块 14001-14999
     */
    GE_NAME_CODE_NOT_MATCH(140001, "使用人姓名和使用人学工号不一致"),

    /**
     * 比特盒子错误码 100001-109999
     */
    BRAIN_TRANSCODING_ISNULL(100001, "登录参数为空"),
    BRAIN_EQUIP_OFFLINE(100002, "盒子设备不在线"),
    BRAIN_EQUIP_REQ_FAIL(100003, "请求盒子服务失败"),
    BRAIN_PHOTOQC_FAIL(100004, "图片质量不合格,图像不合格，请提供正脸、清晰、无遮挡图片"),
    BRAIN_PHOTO_NOT_SIMILAR(100005, "两张图片不相似"),
    BRAIN_ISSPERGROUP_FAIL(100006, "下放权限组失败"),
    BRAIN_ISSADDUSER_FAIL(100007, "下放添加/修改用户失败"),
    BRAIN_ISSDELUSER_FAIL(100008, "下放删除用户失败"),
    BRAIN_PHOTO_ISNULL(100009, "人脸照片为空"),
    BRAIN_GTE_PHOTO_DETECT(100010, "获取照片属性失败"),
    BRAIN_HTTP_ISSUE_USER_ADD(100011, "http方式下放添加用户失败"),
    BRAIN_HTTP_ISSUE_USER_UP(100012, "http方式下放更新用户失败"),
    BRAIN_HTTP_ISSUE_USER_DEL(100012, "http方式下放删除用户失败"),


    /**
     * 视频分析
     */
    VOG_EVENT_RULE_EXIT(110001, "事件规则名已存在"),
    VOG_EVENT_TYPE_NOT_EXIT(110002, "事件类型不存在"),
    VOG_EVENT_HASDEAL(110003, "事件已处理"),
    VOG_EVENT_TREND_INFO(110004, "获取事件发生趋势数据异常"),
    VOG_EVENT_DEAL_INFO(110005, "获取事件发生处理数据异常"),
    VOG_EVENT_WARN_INFO(110006, "获取告警事件数据异常"),
    VOG_EVENT_PRO_INFO(110007, "获取事件占比数据异常"),
    VOG_ADD_EQUIP_FAIL(110008, "视频模块添加设备异常"),
    VOG_MODIFY_EQUIP_FAIL(110009, "视频模块修改设备异常"),
    VOG_DEL_EQUIP_FAIL(110010, "视频模块删除设备异常"),
    VOG_EVENT_FAIL(110011, "事件处理异常"),
    VOG_NOT_EVENT_DATA_FAIL(110012, "没有登记设备的数据上报"),
    VOG_NOT_RULE_DATA_FAIL(110013, "没有对应的规则"),


    /**
     * 教务 120001-129999
     */
    EA_COURSE_TYPE_EXIT(120001, "课程类型已存在"),
    EA_COURSE_TYPE_HS_COURSE(120002, "课程类型下存在课程,不允许删除"),
    EA_TERM_YEAR_EXIT(120003, "该学年下该学期已经存在"),
    EA_YEAR_HAS_TWO_TERM(120004, "每学年下只允许两个学期"),
    EA_SECOND_YEAR_TIME_FAIL(120005, "第二学期的开始时间不能早于第一学期的结束时间"),
    EA_FIRST_YEAR_TIME_FAIL(120006, "第一学期的结束时间不能早于第二学期的开始时间"),
    EA_COURSE_CLASS_NO_EXIT(120007, "课程班级编号已存在"),
    EA_COURSE_CLASS_NAME_EXIT(120008, "课程班级名字已存在"),
    EA_COURSE_CLASS_IS_EXIT(120009, "该学期下该课程的课程班级已存在"),
    EA_COURSE_CLASS_WEEK_FAIL(120010, "课程班级的起始周/结束周必须在所选学期限制的周内"),
    EA_TERM_HAS_COURSE_CLASS(120011, "存在该课程的课程班级，不允许删除"),
    EA_COURSE_ARRANGE_WEEK_FAIL(120012, "排课的起始周/结束周必须在所选课程班级限制的周内"),
    EA_COURSE_CLASS_HAS_ARR(120013, "课程班级下存在排课，不允许删除"),
    EA_TERM_HAS_ARR(120013, "改学期下存在排课，不允许删除"),
    EA_TERM_TIME_ERROR(120014, "学期开始/结束时间与已有学期冲突"),
    EA_EXAM_TYPE_EXIT(120015, "考试类型已存在"),
    EA_EXAM_TYPE_HS_EXAM(120016, "考试类型下存在考试,不允许删除"),
    EA_EXAM_NAME_EXIT(120017, "考试名称已存在"),
    EA_EXAM_ROOM_EXIT(120018, "该教师该时间已有考试存在"),
    EA_EXAM_IS_RUN(120019, "有考试正在进行，不允许删除"),

    /**
     * 考勤 130001-139999
     */
    AT_COURSE_RULE_NAME_EXIT(130001, "课程考勤规则名已存在"),
    AT_EXAM_RULE_NAME_EXIT(130001, "考试考勤规则名已存在"),


    /**
     * 会议 140001-149999
     */
    MT_MEETING_TIME_COVER(140001, "该时间段内会议室已有其他会议"),
    MT_MEETING_RULE_NOT_EXIT(140002, "会议规则不存在"),
    /**
     * 对接 99900-99999
     */
    BU_TREADE_RECORD_ALREADY_EXIST(99938, "该记录已存在"),
    BU_RECORD_ALREADY_REFUND(99939, "该记录已经冲正"),

    /**
     * 通用配置
     */


    COMMAND_IS_ERROR(999, "命令号错误"),
    // 成功
    SUCCESS(200, "成功"),
    // 失败
    FAIL(400, "失败"),
    // 未认证（签名错误）
    UNAUTHORIZED(401, "未认证（签名错误）"),
    // 接口不存在
    NOT_FOUND(404, "接口不存在"),
    // 接口不存在
    TOKEN_ERROR(410, "TOKEN验证错误"),
    //从redsi获取token失败
    REDIS_TOKEN_ERROR(411, "TOKEN失效"),
    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500, "系统错误，请联系管理员"),
    // 参数转换错误
    TRANSCODING_FAIL(409, "参数转换错误"),
    // 参数不完整
    PARAM_FAIL(501, "参数不完整"),

    // 请选择需要操作的信息
    SELECT_IS_NULL(666, "请选择需要操作的信息"),

    // 请选择上级节点
    PARENT_IS_NULL(668, "请选择上级节点"),

    RECORD_MORE(9997, "要导出的记录超过50000条，请重新筛选"),

    DATA_OPERIDS_IS_NULL(9998, "请选择要操作的记录"),

    // 不被允许操作
    OPERATION_NOT_ALLOWED(667, "不被允许操作"),

    DATA_NOT_EXIST(9999, "查询数据不存在！");

    /**
     * 错误代码
     */
    private int code;
    /**
     * 错误消息
     */
    private String msg;

    RetCode(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
