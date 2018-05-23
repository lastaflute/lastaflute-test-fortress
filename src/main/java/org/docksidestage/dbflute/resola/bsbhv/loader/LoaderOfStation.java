package org.docksidestage.dbflute.resola.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.docksidestage.dbflute.resola.exbhv.*;
import org.docksidestage.dbflute.resola.exentity.*;

/**
 * The referrer loader of station as TABLE. <br>
 * <pre>
 * [primary key]
 *     STATION_ID
 *
 * [column]
 *     STATION_ID, STATION_NAME, BIRTHDATE, HOME_COUNT, WORKING_KILOMETER
 *
 * [sequence]
 *     
 *
 * [identity]
 *     
 *
 * [version-no]
 *     
 *
 * [foreign table]
 *     
 *
 * [referrer table]
 *     
 *
 * [foreign property]
 *     
 *
 * [referrer property]
 *     
 * </pre>
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfStation {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<Station> _selectedList;
    protected BehaviorSelector _selector;
    protected StationBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfStation ready(List<Station> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected StationBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(StationBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<Station> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
