<script>
    import InputField from './InputField.svelte';
    let countries = ["한국 - KR", "영국 - UK", "터키 - TR"];
</script>

<div class="max-w-md mx-auto bg-white p-8 mt-10 rounded-lg shadow-lg">
    <h2 class="text-2xl font-bold mb-6">주소 확인</h2>
    <p class="text-sm text-gray-500 mb-4">주소는 게스트의 예약이 확정된 이후에 공개됩니다.</p>

    <div class="mb-4">
        <label for="country" class="block text-sm font-medium text-gray-700 mb-1">국가/지역</label>
        <select id="country" name="country" class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm
        focus:border-2 focus:ring-blue-700 focus:border-blue-700 sm:text-sm">
            {#each countries as country}
            <option>{country}</option>
            {/each}
        </select>
    </div>

    <InputField label="도/특별·광역시" id="province" placeholder="Seoul" />
    <InputField label="도시(해당하는 경우)" id="city" placeholder=""/>
    <InputField label="군/구(해당하는 경우)" id="district" placeholder="Yongsan District"/>
    <InputField label="도로명 주소" id="street-address" placeholder=""/>
    <InputField label="아파트 층수/호수, 건물명(해당하는 경우)" id="apartment" placeholder=""/>
    <InputField label="우편번호(해당하는 경우)" id="postcode" placeholder=""/>
</div>