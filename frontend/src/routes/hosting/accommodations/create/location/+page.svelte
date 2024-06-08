<script>
    import QuestionSection from "../QuestionSection.svelte";
    import RoutingBtn from "../../../../../components/RoutingBtn.svelte";
    import LocationForm from "../../../../../components/LocationForm.svelte";
    import {urlPrefix} from "../Constants.js";
</script>

<QuestionSection title="Q6. 숙소 상세 주소를 확인해주세요">
    <LocationForm />
</QuestionSection>

<div class="flex mt-5 w-[75%] justify-between">
    <RoutingBtn targetUrl="{urlPrefix}/coordinate" btnText="이전"/>
    <RoutingBtn targetUrl="{urlPrefix}/floorPlan" btnText="다음"/>
</div>