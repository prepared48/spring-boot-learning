package com.prepared.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prepared.entity.Tuser;
import com.prepared.mapper.TuserMapper;
import com.prepared.service.ITuserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author prepared
 * @since 2021-09-09
 */
@Service
public class TuserServiceImpl extends ServiceImpl<TuserMapper, Tuser> implements ITuserService {

}
